# Testing strategy
I want to test every methods possible based on the methods function signature

## class Board:

1. ``` public Board(int size) ```:
   I would create an instance of the Board, pass in the parameter size and compare to see if it is not null, if not null it means the contructor successfully create an instance. Then I will check if the board.getSize() (presumably getSize() is reliable), to see if the returned value equals the size parameter we passed in.
   
2. ``` public boolean setCell(int move, int symbol)```: I will first test with invalid move, for example 10 and a symbol value for example 2, the return value should be false. Then I will test with valid move, for example 3, then check if the value of getCell(0,2) equals 2
3. ``` public int getCell(int row, int col) ```
i create an object Board, use setCell(1,2) then check if getCell(0,0) = 2
4. ``` public void setSize(int size) ```
I will first use the constructor to initialize a Board instance with size 3, then i use setSize(4), then i call and check if getSize() == 4
5. ``` public int getSize() ```
   I will first use the constructor to initialize a Board instance with size 3, then i use getSize() to see if its correctly returning 3
6. ``` private int[] mapMoveToCoordinate(int choice) ```
   : I will define correctly mapped values with corresponding input, I will pass 3 to the method and check if the return value is (0,2), or input 7 and check for (2,0).
7. ``` public void render() ```
   I have no idea at the moment, i need a way to perform check the output stream printed out to the console


## class HumanPlayer:
1. ``` public HumanPlayer(int symbol) ```:
   I will create an instance of HumanPlayer with a symbol value, for example 1, then check if the instance is not null. Then I will call getSymbol() to verify it returns the symbol value we passed in (1).

2. ``` public void makeMove(Board board) ```:
   I will create a Board instance and a HumanPlayer instance. Since this method reads from System.in, I need to mock the input stream. I will provide a valid move input (e.g., 5) and verify that board.getCell() at the corresponding position returns the player's symbol. I should also test with invalid inputs (occupied cells or out-of-range values) to ensure the loop prompts again.


## class Player (abstract):
1. ``` public int getSymbol() ```:
   I will create a concrete subclass instance (HumanPlayer or BotPlayer) with a symbol value, for example 2, then call getSymbol() and verify it returns 2.

2. ``` public abstract void makeMove(Board board) ```:
   This is an abstract method, tested through concrete implementations (HumanPlayer and BotPlayer).


## class BotPlayer:
1. ``` public BotPlayer(int symbol) ```:
   I will create an instance of BotPlayer with a symbol value, for example 2, then check if the instance is not null. Then I will call getSymbol() to verify it returns the symbol value we passed in (2).

2. ``` public void makeMove(Board board) ```:
   I will create a Board instance and a BotPlayer instance. The BotPlayer iterates through cells from 1 to size*size and picks the first empty cell. I will verify that after calling makeMove(), at least one cell on the board is occupied by the bot's symbol. I can also test with a partially filled board to ensure the bot picks the first available empty cell.


## class Game:
1. ``` public void setStartingPlayer(int turn) ```:
   I will create a Game instance, set up a list of players, then call setStartingPlayer(1) and verify that players are sorted in ascending order by symbol. Then I will call setStartingPlayer(2) and verify players are sorted in descending order. I should also test with invalid turn value (e.g., 0 or 3) to verify it throws IllegalArgumentException, and test when players list is null to verify it throws IllegalStateException.

2. ``` public Board getBoard() ```:
   I will create a Game instance, set a Board using setBoard(), then call getBoard() and verify it returns the same Board instance we set.

3. ``` public void setPlayers(List<Player> players) ```:
   I will create a Game instance, create a list of players, call setPlayers() with that list, then verify the internal players list is correctly set (e.g., by checking the size or that players are used correctly in play()).

4. ``` public void setBoard(Board board) ```:
   I will create a Game instance, create a Board, call setBoard() with the Board, then verify by calling getBoard() that it returns the same Board instance.

5. ``` public void play() ```:
   This is an integration test. I will set up a Game with a Board and players (HumanPlayer or BotPlayer). Since play() runs until someone wins or draw, I should use BotPlayer for automated testing. I will verify that the method eventually terminates and either prints "Player X won" or "Draw". I can also mock a specific board state to force a win condition.

6. ``` public boolean checkWin(Player player) ```:
   I will create a Game with a Board, set up winning scenarios for each case:
   - Row win: fill an entire row with the player's symbol, verify checkWin() returns true.
   - Column win: fill an entire column with the player's symbol, verify checkWin() returns true.
   - Main diagonal win: fill the main diagonal, verify checkWin() returns true.
   - Anti-diagonal win: fill the anti-diagonal, verify checkWin() returns true.
   - No win: set up a board with no winning line, verify checkWin() returns false.

7. ``` public boolean checkFull() ```:
   I will create a Game with a Board, fill all cells with symbols, then verify checkFull() returns true. Then I will create another scenario with at least one empty cell (value 0), and verify checkFull() returns false.


