import java.io.File;
import java.util.Scanner;

public class Map
{
    char [][]map;
    private int mapWidth;
    private int mapHeight;
    private int playerStartRow;
    private int playerStartCol;
    
    public Map(int level)
    {
        try
        {
            Scanner in = new Scanner(new File("level"+level+".txt"));
            mapHeight = in.nextInt();
            mapWidth = in.nextInt();
            in.nextLine();
            map = new char[mapWidth][mapHeight];
            int row = 0;
            while(in.hasNextLine())
            {
                String line = in.nextLine();
                String [] squares = line.split(" ");
                for(int i=0;i<mapWidth;i++)
                {
                    map[row][i] = squares[i].charAt(0);
                    if(map[row][i] == 'P')
                    {
                        playerStartRow = row;
                        playerStartCol = i;
                        map[row][i] = '_';
                    }
                }
                row++;
            }
        
        }
        catch(Exception e)
        {
        
        }

    }
    
    public char get(int row, int col)
    {
        return map[row][col];
    }
    
    public void set(int row, int col, char symbol)
    {
        map[row][col] = symbol;
    }

    public int getHeight()
    {
        return mapHeight;
    }
    
    public int getWidth()
    {
        return mapWidth;
    }
    
    public int getPlayerStartRow()
    {
        return playerStartRow;
    }
    public int getPlayerStartCol()
    {
        return playerStartCol;
    }    
}