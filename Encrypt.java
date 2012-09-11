import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.color.CMMException;

public class Encrypt implements ActionListener{
//-------------------------------------------------------------------VARIABLES
	
	//Decleration of all variables for a GUI
	private String msg = "";
	
	JFrame f;
	private JPanel p1;
	//secret will contain the message, status will contain all statuses of the program
	private JTextArea secret, status;
	private JScrollPane scrollPane, scrollStatus;
	//Various buttons 
	private JButton encrypt, pic, decrypt;
	//for holding the image to have a message encrypted in it
	private BufferedImage img = null;
	private File file;
	private int w, h, maxCh;
	
	//to find the picture, use JFileChooser
	private String filename;
	private JFileChooser fc;
	
	//MASSIVE array containing all of the binary values for the keys on a keyboard
	private static final int NUM_BINARY = 96;
	private static final String[][] binary = new String[NUM_BINARY][2];
	
	//-------------------------------------------------------------------CONSTRUCTOR
	Encrypt(){
		//initialization of all variables
		f = new JFrame ("In plain sight");
		
		p1 = new JPanel();
		
		//set the size of the TextAreas
		secret = new JTextArea(5, 5);
		status = new JTextArea(12, 20);
		encrypt = new JButton("Encrypt");
		pic = new JButton("Upload Picture");
		decrypt = new JButton("Decrypt");
		//maxCh will be used to calculate the amount of characters a pic can hold
		maxCh = 0;
		
		//File.seperator is a string containing "/" or "\" (depending on OS)
		filename = File.separator+"tmp";
		fc = new JFileChooser(new File(filename));
		
		//All keyboard ASCII characters and their binary values
		binary[0][0] = " "; binary[0][1] = "00100000";
		binary[1][0] = "!"; binary[1][1] = "00100001";
		binary[2][0] = "\""; binary[2][1] = "00100010";
		binary[3][0] = "#"; binary[3][1] = "00100011";
		binary[4][0] = "$"; binary[4][1] = "00100100";
		binary[5][0] = "%"; binary[5][1] = "00100101";
		binary[6][0] = "&"; binary[6][1] = "00100110";
		binary[7][0] = "'"; binary[7][1] = "00100111";
		binary[8][0] = "("; binary[8][1] = "00101000";
		binary[9][0] = ")"; binary[9][1] = "00101001";
		binary[10][0] = "*"; binary[10][1] = "00101010";
		binary[11][0] = "+"; binary[11][1] = "00101011";
		binary[12][0] = ","; binary[12][1] = "00101100";
		binary[13][0] = "-"; binary[13][1] = "00101101";
		binary[14][0] = "."; binary[14][1] = "00101110";
		binary[15][0] = "/"; binary[15][1] = "00101111";
		binary[16][0] = "0"; binary[16][1] = "00110000";
		binary[17][0] = "1"; binary[17][1] = "00110001";
		binary[18][0] = "2"; binary[18][1] = "00110010";
		binary[19][0] = "3"; binary[19][1] = "00110011";
		binary[20][0] = "4"; binary[20][1] = "00110100";
		binary[21][0] = "5"; binary[21][1] = "00110101";
		binary[22][0] = "6"; binary[22][1] = "00110110";
		binary[23][0] = "7"; binary[23][1] = "00110111";
		binary[24][0] = "8"; binary[24][1] = "00111000";
		binary[25][0] = "9"; binary[25][1] = "00111001";
		binary[26][0] = ":"; binary[26][1] = "00111010";
		binary[27][0] = ";"; binary[27][1] = "00111011";
		binary[28][0] = "<"; binary[28][1] = "00111100";
		binary[29][0] = "="; binary[29][1] = "00111101";
		binary[30][0] = ">"; binary[30][1] = "00111110";
		binary[31][0] = "?"; binary[31][1] = "00111111";
		binary[32][0] = "@"; binary[32][1] = "01000000";
		binary[33][0] = "A"; binary[33][1] = "01000001";
		binary[34][0] = "B"; binary[34][1] = "01000010";
		binary[35][0] = "C"; binary[35][1] = "01000011";
		binary[36][0] = "D"; binary[36][1] = "01000100";
		binary[37][0] = "E"; binary[37][1] = "01000101";
		binary[38][0] = "F"; binary[38][1] = "01000110";
		binary[39][0] = "G"; binary[39][1] = "01000111";
		binary[40][0] = "H"; binary[40][1] = "01001000";
		binary[41][0] = "I"; binary[41][1] = "01001001";
		binary[42][0] = "J"; binary[42][1] = "01001010";
		binary[43][0] = "K"; binary[43][1] = "01001011";
		binary[44][0] = "L"; binary[44][1] = "01001100";
		binary[45][0] = "M"; binary[45][1] = "01001101";
		binary[46][0] = "N"; binary[46][1] = "01001110";
		binary[47][0] = "O"; binary[47][1] = "01001111";
		binary[48][0] = "P"; binary[48][1] = "01010000";
		binary[49][0] = "Q"; binary[49][1] = "01010001";
		binary[50][0] = "R"; binary[50][1] = "01010010";
		binary[51][0] = "S"; binary[51][1] = "01010011";
		binary[52][0] = "T"; binary[52][1] = "01010100";
		binary[53][0] = "U"; binary[53][1] = "01010101";
		binary[54][0] = "V"; binary[54][1] = "01010110";
		binary[55][0] = "W"; binary[55][1] = "01010111";
		binary[56][0] = "X"; binary[56][1] = "01011000";
		binary[57][0] = "Y"; binary[57][1] = "01011001";
		binary[58][0] = "Z"; binary[58][1] = "01011010";
		binary[59][0] = "["; binary[59][1] = "01011011";
		binary[60][0] = "\\"; binary[60][1] = "01011100";
		binary[61][0] = "]"; binary[61][1] = "01011101";
		binary[62][0] = "^"; binary[62][1] = "01011110";
		binary[63][0] = "_"; binary[63][1] = "01011111";
		binary[64][0] = "`"; binary[64][1] = "01100000";
		binary[65][0] = "a"; binary[65][1] = "01100001";
		binary[66][0] = "b"; binary[66][1] = "01100010";
		binary[67][0] = "c"; binary[67][1] = "01100011";
		binary[68][0] = "d"; binary[68][1] = "01100100";
		binary[69][0] = "e"; binary[69][1] = "01100101";
		binary[70][0] = "f"; binary[70][1] = "01100110";
		binary[71][0] = "g"; binary[71][1] = "01100111";
		binary[72][0] = "h"; binary[72][1] = "01101000";
		binary[73][0] = "i"; binary[73][1] = "01101001";
		binary[74][0] = "j"; binary[74][1] = "01101010";
		binary[75][0] = "k"; binary[75][1] = "01101011";
		binary[76][0] = "l"; binary[76][1] = "01101100";
		binary[77][0] = "m"; binary[77][1] = "01101101";
		binary[78][0] = "n"; binary[78][1] = "01101110";
		binary[79][0] = "o"; binary[79][1] = "01101111";
		binary[80][0] = "p"; binary[80][1] = "01110000";
		binary[81][0] = "q"; binary[81][1] = "01110001";
		binary[82][0] = "r"; binary[82][1] = "01110010";
		binary[83][0] = "s"; binary[83][1] = "01110011";
		binary[84][0] = "t"; binary[84][1] = "01110100";
		binary[85][0] = "u"; binary[85][1] = "01110101";
		binary[86][0] = "v"; binary[86][1] = "01110110";
		binary[87][0] = "w"; binary[87][1] = "01110111";
		binary[88][0] = "x"; binary[88][1] = "01111000";
		binary[89][0] = "y"; binary[89][1] = "01111001";
		binary[90][0] = "z"; binary[90][1] = "01111010";
		binary[91][0] = "{"; binary[91][1] = "01111011";
		binary[92][0] = "|"; binary[92][1] = "01111100";
		binary[93][0] = "}"; binary[93][1] = "01111101";
		binary[94][0] = "~"; binary[94][1] = "01111110";
		binary[95][0] = "END"; binary[95][1] = "10000000";				
	}

	//-------------------------------------------------------------------LAUNCH
	
	public void launch(){
		//have secret TextArea contain whatever message is in the picture (defaults to "")
		secret.append(msg);
		secret.setLineWrap(true);
		//Don't let the user edit the status textarea
		status.setEditable(false);
		//let both Status and Secret be scrollable
		scrollPane = new JScrollPane(secret);
		scrollStatus = new JScrollPane(status);
		scrollStatus.getVerticalScrollBar().setValue(scrollStatus.getVerticalScrollBar().getMaximum());
		scrollPane.getVerticalScrollBar().setValue(scrollStatus.getVerticalScrollBar().getMaximum());
		
		//and buttons and status to a JPanel
		p1.add(encrypt);
		p1.add(pic);
		p1.add(decrypt);
		p1.add(scrollStatus);
		
		//setup layout for the JFrame (should read (1,2) but it defaults to that given these values
		//anyways
		f.setLayout(new GridLayout (1, 0) );
		//Add both the secret textarea and the JPanel to the JFrame
		f.add(scrollPane);
		f.add(p1);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//makes the red X close the program
		f.setResizable(false);//don't allow it to resize
		f.setSize(500,300);
		f.setVisible(true);//might be helpful
		f.setLocationRelativeTo(null);//set window in the center of the screen
		
		//-------------------------------------------------------------------ACTION LISTENERS
		
		//Add actionlisteners for the buttons
		encrypt.addActionListener(this);
		pic.addActionListener(this);
		decrypt.addActionListener(this);
		
	}
	
	//-------------------------------------------------------------------ACTION EVENTS
	
	public void actionPerformed(ActionEvent a){
		try{
			//if the sources is the encrypt button
			if(a.getSource() == encrypt){
				//save the message in the secret textarea to msg
				msg = secret.getText();
				//if there is a picture loaded and a message tyepe up
				if(img != null && !msg.equals("")){
					//convert the string msg to an array of chars
					char[] str = new char[msg.length()];
					str = msg.toCharArray();
					boolean check = true, end = true;
					int count = 1;
					int n = 0, i, j;
					//loop through every pixel in the picture
					for(i = 0; i < w; i++)
						for(j = 0; j < h; j++){
							//if black pixel, make it almost black
							if(img.getRGB(i,j) == -16777216){
								img.setRGB(i,j,-16777214);
							}
							//if the pixel value is not even
							if(img.getRGB(i,j)%2 != 0)
								//make it even
								img.setRGB(i,j,(img.getRGB(i,j)-1));
							//instead of calling img.getRBG all of the time,
							//just save it as pix
							int pix = img.getRGB(i,j);
							//if the current character n that's being encrypted
							//is still inside the message and under the maxCh the
							//picture can hold, then make the next pixel odd
							if(n < msg.length() && n < maxCh){
								if(check && pix%2 == 0 && count != 8){
									pix-=1;
									//pix = -16777215;
								}
								//for the current letter
								if(!check){
									//search for its binary value
									for(int spot = 0; spot < NUM_BINARY; spot++){
										if(binary[spot][0].charAt(0) == str[n]){
											//set pix to even if binary value is 1
											if(binary[spot][1].charAt(count-1) == '1'){
												pix-=1;
												//pix = -16777215;
											}
										}
									}
								}
								//set the pixel to pix
								img.setRGB(i,j, pix);
								//if 8 bits have been entered, reset count
								if(count%8 == 0){
									count = 0;
									if(!check)
										n++;
									check = false;
								}
								//to the next letter!
								count++;
							}
							//after all of the letters have been entered, attach 1 to the end 
							//(will be checked during decryption part)
							else if(end){
								pix -= 1;
								img.setRGB(i,j,pix);
								end = false;
							}
						}
					try {
						//save picture as a png
						ImageIO.write(img, "PNG", file);
						//update status
						status.append("Encrypted successfully\n");
					} 
					catch (IOException ex){
						//if exception occured, then write to status
						status.append("Error while writing\n");
					}
				}
				//if there is no picture or no message
				else{
					//check if there is an image and output message if there's not
					if(img == null)
						status.append("No image selected \n");
					//same with msg
					else if(msg.equals(""))
						status.append("No message to encrypt\n");
				}
			}
			//if source is to upload a picture
			if(a.getSource() == pic){
				//create a filechooser dialog box
				fc.showOpenDialog(f);
				//choose a file
				file = fc.getSelectedFile();
				try {
					//try to save the file as an image
					img = ImageIO.read(file);
					status.append(file.getName() + " Selected for encryption \n");
				}
				//catch error if file couldn't be read
				catch(IOException e) {
					status.append(e.getMessage());
					status.append("Could not read file");
				}
				catch(CMMException e) {
					status.append(e.getMessage()+"\n");
					status.append("Could not read file\n");
				}
				//set w and h to the image's width and height respectively
				w = img.getWidth();
				h = img.getHeight();
				//the amount of characters supported is the amount of pixels
				//in the picture devided by 8 (8 bits to the byte)
				//as of writing these comments, I can't remember why the - 2
				maxCh = (w*h)/8 - 2;
				status.append("Maximum character count: " + maxCh + "\n");	
				
			}
			//If the decrypt button was pushed
			if(a.getSource() == decrypt){
				boolean check = true, brk = false;
				//if there's no picture loaded, nothing to decrypt
				if(img == null){
					status.append("Nothing to decrypt \n");
				}
				else{
					status.append("Attempting to read message\n");
					String chr = "";
					msg = "";
					int count = 1;
					//for each pixel
					for(int i = 0; i < w; i++){
						for(int j = 0; j < h; j++){
							int pix = img.getRGB(i, j);
							//puts the binary value into chr
							//it would be even if 0, odd if 1
							chr += (pix%2)*-1;
							//if we've counted 8 pixels, that means we have a byte
							if(count%8 == 0){
								if(check){
									//checks to see if this picture has been through the
									//program and just doesn't have a message
									if(!chr.equals("11111110")){
										status.append("No message contained\n");
										brk = true;
										break;
									}
									check = false;
								}
								count = 0;
								//if the character equals the end byte sequence, then break
								if(chr.equals(binary[95][1]))
									break;
								//msg += chr + " ";
								//otherwise, convert the binary value to that of a character
								for(int spot = 0; spot < NUM_BINARY; spot++){
									if(binary[spot][1].equals(chr)){
										//to support new lines, I've made // = /n
										if(spot == 60)
											msg += "\n";
										else
											//add the letter to msg
											msg+=binary[spot][0];
									}
								}
								//msg += "\n";
								chr = "";
							}
							//move on to the next set of pixels
							count++;
						}
						//if brk was set to true or we hit the end sequence, then break from the loop
						if(brk || chr.equals(binary[95][1]))
							break;
						
					}
					//we have checked for a message
					if(!check){
						status.append("Message decrypted successfully\n");
						secret.append(msg);
					}
				}
			}
		}
		//if errors were thrown, just ignore them for now. Nothing I can do
		//that I can think of
		catch(StringIndexOutOfBoundsException str){}
		catch(NumberFormatException nfe){}
		catch(NullPointerException npe){}
	}
	
	//the main function. Create an Encrypt object and launch it
	public static void main(String[] args) {
		Encrypt t = new Encrypt();
		t.launch();
	}

}
