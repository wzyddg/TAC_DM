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

import com.TAC.Model.DBQuerrier;

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
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	public String execute(String command) {
		String request=command.substring(1, command.length()-1);
		String result = "";
		System.out.println(request);
		switch (request.charAt(0)) {
		case '1':
			result=DBQuerrier.getDeviceList(request.substring(2));
			break;
		case '2':
			result=DBQuerrier.getRecordList();
			break;
		case '3':
			result=DBQuerrier.getDevice(request.substring(2));
			break;
		case '4':
			result=DBQuerrier.getRecord(request.substring(2));
			break;
		case '5':
			result=DBQuerrier.borrowItem(request.substring(2));
			break;
		case '6':
			result=DBQuerrier.returnItem(request.substring(2));
			break;
		case '7':
		result=DBQuerrier.adminLogin(request.substring(2));
			break;
		case '8':
		result=DBQuerrier.getDeviceListAsAdmin(request.substring(2));
			break;
		case '9':
		result=DBQuerrier.editLeftNumber(request.substring(2));
			break;

		default:
			result=DBQuerrier.wrongCode(request.substring(2));
			break;
		}
		System.out.println(result);
		return result;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			System.out.println("---start Service----");
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream())), true);
			String request = "";
			while (true) {
				String str = in.readLine();
				request = request + str;
				System.out.println(request);
				if (request.contains("]")) {
					System.out.println("Request:" + str);
					// out.println("Request:" + str);
					String resultString = execute(request);
					out.println(resultString);
					out.flush();
					break;
				}
				// else { // closeconnection
				// System.out.println("read nulg and out");
				// break;
				// }
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
