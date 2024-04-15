
def solution(maze,i,j,ans):
    if i == len(maze)-1 and j == len(maze[0])-1:
        ans += ""
        return True

def is_safe(maze,row,col):
    return (row>=0 and col>=0 and row < len(maze) and col < len(maze) and maze[row][col] == 1 )


maze = [[1,0,0,0],
        [1,1,0,1],
        [0,1,0,0],
        [1,1,1,1]]