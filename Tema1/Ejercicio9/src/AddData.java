import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class AddData {
    public static void main(String[] args) throws IOException {
        File file=new File(args[0]+File.separator+args[1]);

        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);

        String line;
        long cont=0;
        BigDecimal total=new BigDecimal(0);

        while ((line=br.readLine()) != null){
            //System.out.println("cont:"+cont);
            BigDecimal bd=new BigDecimal(line);
            total=total.add(bd);
            //System.out.println("total:"+total);
            cont++;
        }
        System.out.println(total);
    }
}