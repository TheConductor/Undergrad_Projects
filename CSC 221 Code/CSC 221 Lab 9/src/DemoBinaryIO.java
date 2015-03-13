import java.io.*;

class DemoBinaryIO {

	public static void main(String[] args) {

		int data = 1094795585;

		demoBinaryOutput(data);

		int x = demoBinaryInput();
		System.out.println("Read in " + x);


	}

	private static int demoBinaryInput() {
		// TODO Auto-generated method stub
		int value = 0;
		
		File inFile = new File("binaryData.dat");
		FileInputStream fis = null;
		DataInputStream inDataStream = null;

		try {
			fis = new FileInputStream(inFile);
			inDataStream = new DataInputStream(fis);
			value = inDataStream.readInt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
		

	}

	

	private static void demoBinaryOutput(int data) {
		File outFile = new File("binaryData.dat");
		FileOutputStream outStream;
		DataOutputStream outDataStream = null;
		try {
			outStream = new FileOutputStream(outFile);
			outDataStream = new DataOutputStream(outStream);
			outDataStream.writeInt(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outDataStream != null)
				try {
					outDataStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
// 1094795585
