import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordGame {
	private LetterTile[][] tiles;
	GameBoard gb;

	public static void main(String[] args) {
		WordGame wg = new WordGame();
		wg.play();
	}// End Main
	
	public WordGame() {
		tiles = createTiles();
		gb = new GameBoard(tiles);
	}// End Word Game
	private void play() {
		Scanner s = new Scanner(System.in);
		String word;
		do {
			System.out.println("Enter word to find: ");
			word = s.next();
			// reset all highlighted tiles
			gb.reset();
			search(word);
		} while (!word.equals("QUIT"));
		gb.dispose();
	}// End play
	
	//Nothing to be done above
	//Complete all the methods below
	
	/**
	 	Variables;
	 		rows = the number of rows in the LetterTiles array tiles[][]
	 		col = the number of columns in the LetterTiles array tiles[][]
	 		r = counter for loops use to move through rows
	 		c = counter for loops use to move through col
	 		other lower case single letter variables used in for loops are counters  
	 */
	
	private LetterTile [][] createTiles(){
		//From the data in the file Letters.txt determine the size (number of rows and number of columns) for the tiles array
		//Instantiate a two dimensional array of the appropriate size and assign it to tiles
		//open the file Letters.txt and read the letters in it
		//use the letters to create LetterTiles
		//add those LetterTiles to the tiles array
		
		String fileName = "Letters.txt";
		File myFile = new File(fileName);
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// End try catch
		int rows = 0, col = 0;
		while(inputFile.hasNext()){
			col = inputFile.nextLine().length();
			rows++;
		}// End While
		col = col/2;
		tiles = new LetterTile[rows][col];
		Scanner inputFile1 = null;
		try {
			inputFile1 = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int r=0; r<rows; r++){
			for(int c=0; c<col; c++){
				char letter = inputFile1.next().charAt(0);
				LetterTile newTile = new LetterTile(letter);
				tiles[r][c]=newTile;
			}// End For
		}// End For
		return tiles;		
	}// End createTiles

	private void search(String word) {
		int i = 0;
		while(!word.equals("QUIT") && i!=1){
			if(searchLeftToRight(word) == false){
				if (searchRightToLeft(word) == false){
					if (searchTopToBottom(word) == false){
						if (searchBottomToTop(word) == false){
							if (searchDiagonalDownLeftToRight(word) == false){
								if (searchDiagonalUpLeftToRight(word) == false){
									if (searchDiagonalDownRightToLeft(word) == false){
										if (searchDiagonalUpRightToLeft(word) == false){
											System.out.println("Sorry but "+word+" was not found.");
										}else{
											System.out.println("Congratulations "+word+" was found!");
										}// End Diagonal Up Right To Left
									}else{
										System.out.println("Congratulations "+word+" was found!");
									}// End Diagonal Down Right To Left
								}else{
									System.out.println("Congratulations "+word+" was found!");
								}// End Diagonal Up Left To Right
							}else{
								System.out.println("Congratulations "+word+" was found!");
							}// End Diagonal Down Left To Right
						}else{
							System.out.println("Congratulations "+word+" was found!");
						}// End Bottom To Top
					}else{
						System.out.println("Congratulations "+word+" was found!");
					}// End Top To Bottom
				}else{
					System.out.println("Congratulations "+word+" was found!");
				}// End Right to left
			}else{
				System.out.println("Congratulations "+word+" was found!");
			}// End Left to Right
			i++;
		}// End While
	} // End search
	
	private boolean searchLeftToRight(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord= "";
		for(int r = 0; r<rows ; r++){
			for(int c = 0; c<col; c++){
				for(int j=0; j<word.length(); j++){
					if(j+c<col){
						foundWord = foundWord + tiles[r][c+j].getLetter();
						if(foundWord.equals(word)){
							returnValue=true;
							for(int highLighter=0; highLighter<word.length(); highLighter++){
								tiles[r][c+highLighter].highLight();
							}// End For to Highlight tiles
							j=word.length();
							c=col;
							r=rows;
						}// End If to test foundWord against Word			
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord="";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchLeftToRight

	private boolean searchRightToLeft(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord= "";
		for(int r = rows-1; r>0 ; r--){
			for(int c = col-1; c>0; c--){
				for(int j=0; j<word.length(); j++){
					if(c-j>0){
						foundWord = foundWord + tiles[r][c-j].getLetter();
						if(foundWord.equals(word)){
							returnValue=true;
							for(int highLighter=word.length()-1; highLighter>=0; highLighter--){
								tiles[r][c-highLighter].highLight();
							}// End For to Highlight tiles
							j=0;
							c=0;
							r=0;
						}// End If to test foundWord against Word			
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord="";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End 

	private boolean searchTopToBottom(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord= "";
		for(int c = 0; c<col; c++){
			for(int r = 0; r<rows; r++){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(wordLength+r<rows){
						foundWord = foundWord + tiles[r+wordLength][c].getLetter();
						if(foundWord.equals(word)){
							returnValue=true;
							for(int highLighter=0; highLighter<word.length(); highLighter++){
								tiles[r+highLighter][c].highLight();
							}// End For to Highlight tiles
							wordLength=word.length();
							c=col;
							r=rows;
						}// End If to test foundWord against Word			
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord="";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchTopToBottom

	private boolean searchBottomToTop(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord= "";
		for(int c = col-1; c>0; c--){
			for(int r = rows-1; r>0 ; r--){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(r-wordLength>0){
						foundWord = foundWord + tiles[r-wordLength][c].getLetter();
						if(foundWord.equals(word)){
							returnValue=true;
							for(int highLighter=0; highLighter<word.length(); highLighter++){
								tiles[r-highLighter][c].highLight();
							}// End For to Highlight tiles
							wordLength=0;
							c=0;
							r=0;
						}// End If to test foundWord against Word			
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord="";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchBottomToTop

	private boolean searchDiagonalDownLeftToRight(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord="";
		for(int i=1; i<=rows; i++){
			for(int r=rows-i, c=0; r<=rows-1 && c<i; r++, c++){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(r+wordLength<rows && c+wordLength<col){
						foundWord= foundWord+tiles[r+wordLength][c+wordLength].getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if(foundWord.equals(word)){
					returnValue=true;
					for(int highLighter=0; highLighter<word.length(); highLighter++){
						tiles[r+highLighter][c+highLighter].highLight();
					}// End For to Highlight tiles
					c=i+1;
					i=rows;
				}else{
					foundWord="";	
				}//End if-else to Detrimine if foundWord equals word
			}// End for to structure count of r & c
		}// End for to structure count to first max diagonal
		for(int i=1; i<=col-1; i++){
			for(int r=0, c=i; r<=rows-1 && c<=col-1; r++, c++){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(r+wordLength<rows && c+wordLength<col){
						foundWord= foundWord+tiles[r+wordLength][c+wordLength].getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if(foundWord.equals(word)){
					returnValue=true;
					for(int highLighter=0; highLighter<word.length(); highLighter++){
						tiles[r+highLighter][c+highLighter].highLight();
					}// End For to Highlight tiles
					r=rows;
					i=col;
				}else{
					foundWord="";	
				}//End if-else to Detrimine if foundWord equals word
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalDownLeftToRight

	private boolean searchDiagonalUpLeftToRight(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord="", line="";
		for(int i=0; i<rows; i++){
			for(int r=i, c=0; r>=0 && c<=i; r--, c++){
				line = line+tiles[r][c].getLetter();
			}// End count to structure count of r & c
			if(i>=(word.length()-1)){
				for(int lineCounter=0; lineCounter<=line.length(); lineCounter++){
					if(lineCounter+word.length()<=(line.length())){
						foundWord=line.substring(lineCounter, (lineCounter+word.length()));
						if(foundWord.equals(word)){
							returnValue=true;
							for(int highLighter=0; highLighter<word.length(); highLighter++){
								tiles[i-lineCounter-highLighter][lineCounter+highLighter].highLight();
							}// End For to Highlight tiles
							lineCounter=line.length()+1;
							i=rows;
						}else{
							foundWord="";	
						}//End if-else to Detrimine if foundWord equals word
					}// End if to stop Bounds Errors
				}// End for to count through the String line
			}// End if to prevent unnecessary searching
			foundWord="";
			line="";
		}// End for to structure count to after first max diagonal
		for(int j=1; j<=col-1; j++){
			for(int r=rows-1, c=j; r>=0 && c<=col-1; r--, c++){
				line = line+tiles[r][c].getLetter();
			}// End count to structure count of r & c
			for(int lineCounter=0; lineCounter<=line.length(); lineCounter++){
				if(lineCounter+word.length()<=(line.length())){
					foundWord=line.substring(lineCounter, (lineCounter+word.length()));
					if(foundWord.equals(word)){
						returnValue=true;
						for(int highLighter=0; highLighter<word.length(); highLighter++){
							tiles[rows-1-lineCounter-highLighter][j+lineCounter+highLighter].highLight();
						}// End For to Highlight tiles
						lineCounter=line.length()+1;
						j=col;
					}else{
						foundWord="";	
					}//End if-else to Detrimine if foundWord equals word
				}// End if to stop Bounds Errors
			}// End for to count through the String line
			foundWord="";
			line="";
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalDownLeftToRight

	private boolean searchDiagonalDownRightToLeft(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord="";
		for(int i=0; i<rows; i++){
			for(int r=rows-i, c=col-1; r<=(rows-1) && c>=0; r++, c--){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(r+wordLength<=(rows-1) && c-wordLength>=0){
						foundWord= foundWord+tiles[r+wordLength][c-wordLength].getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if(foundWord.equals(word)){
					returnValue=true;
					for(int highLighter=0; highLighter<word.length(); highLighter++){
						tiles[r+highLighter][c-highLighter].highLight();
					}// End For to Highlight tiles
					r=-1;
					i=rows;
				}else{
					foundWord="";	
				}//End if-else to Detrimine if foundWord equals word
			}// End for to structure count of r & c
		}// End for to structure count to first max diagonal
		for(int i=0; i<col; i++){
			for(int r=0, c=(col-1)-i; r<=rows-1 && c>=0; r++, c--){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(r+wordLength<=rows-1 && c-wordLength>=0){
						foundWord= foundWord+tiles[r+wordLength][c-wordLength].getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if(foundWord.equals(word)){
					returnValue=true;
					for(int highLighter=0; highLighter<word.length(); highLighter++){
						tiles[r+highLighter][c-highLighter].highLight();
					}// End For to Highlight tiles
					r=-1;
					i=col;
				}else{
					foundWord="";	
				}//End if-else to Detrimine if foundWord equals word
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalDownRightToLeft

	private boolean searchDiagonalUpRightToLeft(String word) {
		boolean returnValue = false;
		int rows = tiles.length, col = tiles[0].length;
		String foundWord="";
		for(int i=0; i<rows; i++){
			for(int r=i, c=col-1; r>=0 && c>=(col-1)-i; r--, c--){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(r-wordLength>=0 && c-wordLength>=(col-1)-i){
						foundWord=foundWord+tiles[r-wordLength][c-wordLength].getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if(foundWord.equals(word)){
					returnValue=true;
					for(int highLighter=0; highLighter<word.length(); highLighter++){
						tiles[r-highLighter][c-highLighter].highLight();
					}// End For to Highlight tiles
					r=-1;
					i=rows;
				}else{
					foundWord="";	
				}//End if-else to Detrimine if foundWord equals word
			}// End for to structure count of r & c
		}// End for to structure count to first max diagonal
		for(int i=0; i<col; i++){
			for(int r=rows-1, c=(col-1)-i; r>=0 && c>=(col-1-i)-(rows-1) && c>=0; r--, c--){
				for(int wordLength=0; wordLength<word.length(); wordLength++){
					if(r+wordLength>=0 && c-wordLength>=(col-1-i)-(rows-1) && c-wordLength>0){
						foundWord= foundWord+tiles[r-wordLength][c-wordLength].getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if(foundWord.equals(word)){
					returnValue=true;
					for(int highLighter=0; highLighter<word.length(); highLighter++){
						tiles[r-highLighter][c-highLighter].highLight();
					}// End For to Highlight tiles
					r=-1;
					i=col;
				}else{
					foundWord="";	
				}//End if-else to Detrimine if foundWord equals word
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalUpRightToLeft
}// End Class
