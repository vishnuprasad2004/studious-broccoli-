R = '\x1b[31mR\x1b[0m'
G = '\x1b[32mG\x1b[0m'
B = '\x1b[34mB\x1b[0m'
M = '\x1b[35mM\x1b[0m'

# print(R,G,B,M)

def print_colorboard(matrix):
    print("==== Color Board ====")
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            print(matrix[i][j],end=" ")
        print()



def fill(colorboard, oldcolor, newcolor, x, y):
    if x<0 or x>len(colorboard)-1 or y<0 or y>len(colorboard[0])-1 or colorboard[x][y] != oldcolor:
        return
    if colorboard[x][y] == newcolor:   
        return
    colorboard[x][y] = newcolor
    fill(colorboard, oldcolor, newcolor, x+1, y)
    fill(colorboard, oldcolor, newcolor, x-1, y)
    fill(colorboard, oldcolor, newcolor, x, y+1)
    fill(colorboard, oldcolor, newcolor, x, y-1)



colorboard = [[R,R,B,M,M,B],
              [R,R,B,R,M,M],
              [B,B,B,B,B,M],
              [R,R,B,B,R,R],
              [R,R,B,R,R,G],
              [R,B,B,B,G,G]]

oldcolor, newcolor = R,G
x,y = 1,1

print_colorboard(colorboard)
fill(colorboard,oldcolor,newcolor,x,y)
print_colorboard(colorboard)

