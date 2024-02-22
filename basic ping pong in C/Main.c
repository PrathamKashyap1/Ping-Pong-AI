#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<windows.h>
#include <conio.h>


int i=0,j=0,x=1,y=1,p1y=2,p2y=28,ballx=2,bally=5;
 int p1x=2,p2x=28;
int gameover=0;
char in=NULL;

void draw()
{
    system("Cls");
    printf("\t\t\t\tPRATHAM'S PING PONG\n\n\n\n");
    for(i=0;i<=30;i++)
        printf("#");
    
    for(i=0;i<=30;i++)
    {
    for(j=0;j<=30;j++)
    {
        if(j==0||j==30)
        {
            printf("#");
        }
        else if(j==p1y&&(i==p1x||i==p1x-1||i==p1x+1))
        {
            printf("|");
        }
        else if(j==p2y&&(i==p2x||i==p2x-1||i==p2x+1))
        {
            printf("|");
        }
        else if(i==ballx&&j==bally)
        {
            printf("O");
        }
        else{
            printf(" ");
        }

    }
    printf("\n");
    }
    for(i=0;i<=30;i++){
        printf("#");}
    ballx+=x;
    bally+=y;
    sleep(0.4);

    
}
void logic()
{
    if(ballx==p1x||ballx==p1x+1||ballx==p1x-1)
    {
        y=1;
    }
    if(ballx==p2x||ballx==p2x-1||ballx==p2x+1)
    {
        y=-1;
    }
    if(ballx==30)
    {
        x=-1;
    }
    if(ballx==0)
    {
        x=1;
    }
    if(bally==0)
    {
        printf("\n\n\n\n\n\nPLAYER 2 WINS");
        gameover=1;
    }
    if(bally==30)
    {
        printf("\n\n\n\n\n\nPLAYER 1 WINS");
        gameover=1;
    }

}
void input()
{
    
    in=getch();
    switch (in)
    {
    case 'w':
        p1x--;
        break;
    case 's':
        p1x++;
        break;
    case '8':
        p2x--;
        break;
    case '5':
        p2x++;
        break;
    
    default:
        break;
    }
    }
    
void main()
{
    printf("PRESS ANY KEY TO START\tW,S are controls for player 1 and 5 and 8 are controls for player 2");
    getch();
    while(!gameover)
    {
        draw();
        if(kbhit())
        {
        input();
        }
        logic();
    }

}