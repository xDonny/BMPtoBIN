import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;


public class BMPtoBIN 
{

	public static String usage = "USAGE: java BMPtoBIN filename.bmp output.ex char-size";
	
	public static void main(String[] args) throws Exception
	{
		if (args.length > 0)
		{
			if (args[0].contains(".bmp"))
			{
				File in = new File(System.getProperty("user.dir")+ "\\" + args[0]);
				File out = new File(System.getProperty("user.dir")+ "\\" + args[1]);
				
				PrintWriter writer = new PrintWriter(out, "UTF-8");
				
				BufferedImage img = ImageIO.read(in);
				
				int square = 0;
				try
				{
					square = Integer.parseInt(args[2]);
				}
				catch(Exception e)
				{
					System.out.println("Please try entering a number for the size of character (must be a square");
					writer.close();
					return;
				}
				
				for (int a = 0; a < img.getHeight()/square; a++)
				{
					for (int z = 0; z < img.getWidth()/square; z++)
					{
						for (int i = a * square; i < (a * square) +square; i++)
						{
							byte current = 0;
							for(int j = z * square; j < (z * square) + square; j++)
							{
								if (img.getRGB(j, i) == img.getRGB(0, 0))
								{
									current = (byte) (current << 1);
								}
								else
								{
									current = (byte) (current << 1);
									current = (byte) (current | 0x01);
								}
							}
							String s2 = String.format("%8s", Integer.toBinaryString(current & 0xFF)).replace(' ', '0');
							writer.println(s2);
						}
					}
				}
				writer.close();
			}
			else
			{
				System.out.println(usage);
			}
		}
		else
		{
			System.out.println(usage);
		}
	}
	
}
