/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rainbowtabletask;

/**
 *
 * @author paddy
 */
public class Table {


    int kNUM_ROWS;
    int kNUM_COLUMNS;
    int[][] rainbowTable;

    public Table()
    {
        
    }
    
    public Table(int num_rows, int num_columns)
    {
        kNUM_ROWS = num_rows;
        kNUM_COLUMNS = num_columns;
    }
    
    public void set_table_rows(int num_rows)
    {
        kNUM_ROWS = num_rows;
    }
    
    public void set_table_columns(int num_columns)
    {
        kNUM_COLUMNS = num_columns;
    }

    // Random Number Generator (seed, row_num)
    public int random_number_generator(int seed, int row_num)
    {
        int result = 0;
        // TODO: rng code
        return result;
    }
    
    // only call after rows and columns set
    public void seed_table(int seed)
    {
        rainbowTable = new int [kNUM_ROWS][2];

        for (int i = 0; i < kNUM_ROWS; i++)
        {
            int plaintext = random_number_generator(seed, i);
            rainbowTable[i][0] = plaintext;
            
            // do first column (setting up loop)
            int newhash = 0; // SHA1(plaintext);
            // for rest of columns, calculate chain
            for(int x = 0; x < kNUM_COLUMNS - 1; x++)
            {
                plaintext = reduce_function(reduce_function_generator(x), newhash);
                //newhash = SHA1(plaintext);
            }
            // store chain end
            rainbowTable[i][1] = newhash;
        }
    }
    
    // Reduce Function Generator (column_num)
    public int reduce_function_generator(int column_num)
    {
        int result = 0;
        // TODO: generate configuration for reduce function based on column number
        return result;
    }

    // Reduce function takes a generated configuration and hash, outputs a corresponding plaintext. 
    public int reduce_function(int reduce_configuration, int hash)
    {

        //TODO: apply a reduce function, based on the previously generated configuration, to the hash
        return 0; 
    }

    // Lookup hash at specific location in table. If matches, return corresponding plaintext. If not, return -1.
    public int lookup_hash_in_column(int hash, int column_num, int row_num)
    {
        int found = -1;
        int new_hash = hash;
        int plaintext = 0;
        int col = column_num;
        while (col < kNUM_COLUMNS)
        {
            plaintext = reduce_function(reduce_function_generator(col), new_hash);
            //new_hash = SHA1(plaintext);
        }
        if (new_hash == rainbowTable[row_num][1])
        {
            found = plaintext;
        }
        return found;
    }

    // Search entire table for hash. If found, return corresponding plaintext. If not, return -1.
    public int lookup_hash(int hash)
    {
        int found = -1;
        int row_num = 0;
        while (found == -1 && row_num < kNUM_ROWS)
        {
            int column_num = kNUM_COLUMNS -1;
            while (found == -1 && column_num >= 0)
            {
                found = lookup_hash_in_column(hash, column_num, row_num);
                column_num--;
            }
            row_num++;
        }
        return found;
    }
    
}
