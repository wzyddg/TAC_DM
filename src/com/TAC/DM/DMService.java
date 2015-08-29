package com.TAC.DM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class DMService implements Runnable {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;

	public DMService(Socket serviceSocket) {
		this.clientSocket = serviceSocket;
	}

	public String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// �þɵ��ַ���������ַ�����������ܻ�����쳣��
			byte[] bs = str.getBytes(oldCharset);
			// ���µ��ַ����������ַ���
			return new String(bs, newCharset);
		}
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			System.out.println("---start Service----");
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

			while (true) {
				String str = in.readLine();
				if (str != null) {
					System.out.println("Request:" + str);
					String aString = "aasdf";
					out.println("backdfds");
					out.flush();
				} else { // closeconnection
					System.out.println("read nulg and out");
					break;
				}
			}
			out.close();
			in.close();
			clientSocket.close();

			// Close the connection, but not the server socket
			System.out.println("--end service--");
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}
}
