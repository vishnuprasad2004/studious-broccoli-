#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define WIDTH 80   // Width of the canvas
#define HEIGHT 40  // Height of the canvas
#define MARKER '@'

char canvas[HEIGHT][WIDTH];

// Set a point on the canvas
void setPixel(int x, int y) {
    if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
        canvas[y][x] = MARKER;
    }
}

// Plot all 8 symmetric points for a circle
void plotCirclePoints(int xc, int yc, int x, int y) {
    setPixel(xc + x, yc + y);
    setPixel(xc - x, yc + y);
    setPixel(xc + x, yc - y);
    setPixel(xc - x, yc - y);
    setPixel(xc + y, yc + x);
    setPixel(xc - y, yc + x);
    setPixel(xc + y, yc - x);
    setPixel(xc - y, yc - x);
}

// Bresenham’s Circle Algorithm - 8 ways symmetry concept
void drawCircle(int xc, int yc, int r) {
    int x = 0, y = r;
    int d = 3 - 2 * r;

    while (x <= y) {
        plotCirclePoints(xc, yc, x, y);
        if (d <= 0)
            d += 4 * x + 6;
        else {
            d += 4 * (x - y) + 10;
            y--;
        }
        x++;
    }
}

// Print the canvas
void printCanvas() {
    for (int i = 0; i < HEIGHT; i++) {
        for (int j = 0; j < WIDTH; j++) {
            if(canvas[i][j] == MARKER) {
              printf("%c ", canvas[i][j]);
            } else {
              printf("..");
            }
        }
        printf("\n");
    }
}

int main() {
    // Initialize canvas with blank spaces
    memset(canvas, ' ', sizeof(canvas));

    int xc = WIDTH / 2;
    int yc = HEIGHT / 2;
    int radius;

    printf("Enter radius of the circle (max %d): ", HEIGHT / 2 - 1);
    scanf("%d", &radius);

    drawCircle(xc, yc, radius);
    printCanvas();

    return 0;
}
