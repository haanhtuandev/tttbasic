#!/bin/bash

# Configuration
SERVER_IP="127.0.0.1"
SERVER_PORT=8080        # Change this to match your server's port
NUM_USERS=10000         # Total connections to attempt
CONCURRENCY_DELAY=0.001 # Delay between connections in seconds (to flood quickly)

echo "========================================================"
echo " Starting Tic-Tac-Toe Server Stress Test"
echo " Attempting to spawn $NUM_USERS concurrent connections..."
echo "========================================================"

# Track spawned process IDs
pids=()

# Loop to spawn 10,000 background clients
for ((i=1; i<=NUM_USERS; i++))
do
    # Netcat connects to the server. 
    # 'sleep 10' keeps the connection open for 10 seconds before closing.
    # Redirecting stdout/stderr to /dev/null keeps the terminal clean.
    sleep 10 | nc $SERVER_IP $SERVER_PORT > /dev/null 2>&1 &
    
    # Capture the PID of the netcat process we just put in the background
    pids+=($!)

    # Optional: print progress every 1000 connections
    if (( i % 1000 == 0 )); then
        echo "-> Sent $i connections..."
    fi

    # Tiny delay to prevent bash itself from choking during the loop execution
    sleep $CONCURRENCY_DELAY
done

echo "--------------------------------------------------------"
echo "All $NUM_USERS connection attempts dispatched."
echo "Waiting 5 seconds to observe server state..."
echo "--------------------------------------------------------"
sleep 5

# Check if the server process is still alive (assuming it runs on java)
if pgrep -x "java" > /dev/null; then
    echo "✅ SUCCESS: The Java server is STILL RUNNING."
    echo "This means your thread pool / socket queue handled the load safely!"
else
    echo "💥 CRASHED: The Java server is NO LONGER RUNNING."
    echo "It likely ran out of memory (OOM) or hit OS thread limits!"
fi

echo "Cleaning up background test clients..."
# Kill any remaining netcat processes spawned by this script
kill "${pids[@]}" > /dev/null 2>&1
echo "Cleanup complete."