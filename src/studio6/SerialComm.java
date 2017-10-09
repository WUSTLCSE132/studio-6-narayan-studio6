package studio6;

import java.util.Scanner;

import jssc.*;

public class SerialComm {

	static SerialPort port;

	private static boolean debug;  // Indicator of "debugging mode"

	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	


	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);

		debug = true; // Default is to NOT be in debug mode
	}

	// TODO: Add writeByte() method from Studio 5
	static void writebyte(byte c) throws SerialPortException
	{
		if (debug)
		{
			port.writeByte(c);
			System.out.println("<0x" + Integer.toHexString(c) + ">");
		}
		else
		{
			port.writeByte(c);
		}

	}

	// TODO: Add available() method
	static boolean available() throws SerialPortException {
		if (port.getInputBufferBytesCount() > 0)
		{
			byte c = port.readBytes(1)[0];
			if (c != 10 && c != 13)
			{
				return true;
			}
		}
		return false;

	}

	// TODO: Add readByte() method	
	static byte readByte() throws SerialPortException {
		if (debug)
		{
			byte c = port.readBytes(1)[0];
			System.out.println("<" + String.format("%02x", c) + ">");
			return c;
		}
		else {
			return port.readBytes(1)[0];
		}
			
	}

	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException 
	{
		Scanner sys = new Scanner(System.in);
		SerialComm port = new SerialComm("/dev/cu.usbserial-DN02AZZB") ;
		while (true)
		{
			if (port.available())
			{
				byte in = port.readByte();
				System.out.println((char) in);
			}
		}
	}


}

