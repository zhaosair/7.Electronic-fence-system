package fence.service.impl;

import fence.service.ISocketService;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.concurrent.TimeUnit;

@Service
public class SocketService implements ISocketService {


    @Override
    public void doWork() {
        Socket client = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try {
            client = new Socket();
            InetAddress inetAddress = InetAddress.getLocalHost();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, 6666);
            client.connect(inetSocketAddress);
            inputStream = client.getInputStream();
            outputStream = client.getOutputStream();
            writer = new ObjectOutputStream(outputStream);
            if (client.isConnected()) {
                writer.writeInt(999);
                writer.flush();
            }
            reader = new ObjectInputStream(inputStream);
            int length = 1;
            while (length != -1) {
                length = reader.readInt();
                byte[] bytes = new byte[length];
                reader.read(bytes);
                System.out.println(new String(bytes));

                writer.writeInt(999);
                writer.flush();

                TimeUnit.SECONDS.sleep(3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
                if(writer!=null){
                    writer.close();
                }
                if(reader!=null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
