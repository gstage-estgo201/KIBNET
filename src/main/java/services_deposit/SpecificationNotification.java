package services_deposit;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SpecificationNotification {

//		Van사 서버 정보
//	- TEST: 119.205.217.5/20101
//	- REAL: 222.122.82.61/20101
	private final static String HOST = "119.205.217.5";
	
	public void Method() {
		try ( Socket socket = new Socket(HOST, 20101);) {
			System.out.println(socket.isConnected());
			socket.setSoTimeout(300);
			try(OutputStream sender = socket.getOutputStream();
				InputStream receiver = socket.getInputStream();) {
				
				byte[] receivedData = new byte[1024];
				receiver.read(receivedData);
				
				System.out.println("received data : " + receivedData);
				
				String message = "OK";
				byte[] sendData = message.getBytes();
				sender.write(sendData);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
