def print_checkboard(matrix):
    print("==== Chess Board ====")
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            print(matrix[i][j],end=" ")
        print()

def generate_chessboard(n):
    return [['X' for j in range(n)] for i in range(n)]


def n_queens_solution(board, row):
    if row == len(board):
        print_checkboard(board)
        return
    
    for j in range(len(board[0])):
        if is_safe(board, row, j):
            board[row][j] = 'Q'
            n_queens_solution(board,row+1)
            board[row][j] = 'X'


def is_safe(board, row, col):
    # vertically upwards
    for i in range(row-1,-1,-1):
        if board[i][col] != 'X':
            return False
        
    # diagonally left side
    for i,j in zip(range(row-1,-1,-1),range(col-1,-1,-1)):
        if board[i][j] != 'X':
            return False
    
    # diagonally right side
    for i,j in zip(range(row-1,-1,-1),range(col+1,len(board[0]),1)):
        if board[i][j] != 'X':
            return False
        
    return True


chessboard = generate_chessboard(8)
n_queens_solution(chessboard,0)