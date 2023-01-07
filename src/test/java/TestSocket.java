import com.nixe.pinup.utils.ListingSOCKET;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class TestSocket {
    @Test
    public void testSocket() throws IOException {
        ListingSOCKET l = new ListingSOCKET(1313);
    }
    @Test
    private void initCliente() throws IOException {
        Socket cliente = new Socket("0.0.0.0",7845);
        String msg = "Enviando mensagem para o socket";
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(msg);
    }
    @Test
    private void calldog() throws Exception {
        ListingSOCKET l = new ListingSOCKET(1313);
        //l.audio();
    }
}
