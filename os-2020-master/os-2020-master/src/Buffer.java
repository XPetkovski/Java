import io.IOManager;
import io.IOManagerImpl;

import java.io.*;

public class InputStreamTester {
    public static void main(String[] args) throws FileNotFoundException {


        IOManager ioManager = new IOManagerImpl();
        ioManager.copyFileByteByByte(new File("dat2.txt"), new File("dat3.txt"), false);
        ioManager.copyFileWithCustomByteBuffer(new File("dat2.txt"), new File("dat4.txt"), false);
        ioManager.copyFileWithJavaByteBuffer(new File("dat2.txt"), new File("dat5.txt"), false);
        ioManager.copyFileCharByChar(new File("dat2.txt"), new File("dat6.txt"), false);
        ioManager.copyFileCharByCharBuffered(new File("dat2.txt"), new File("dat7.txt"), false);
        ioManager.copyFileLineByLine(new File("dat2.txt"), new File("dat8.txt"), false);
        ioManager.copyFileWithConvertingByteStreamToCharStreamWithCharset(new File("dat2.txt"), new File("dat9.txt"), false,"UTF-8");
        ioManager.copyFileContentReversed(new File("normal.txt"), new File("reversed.txt"));

        ioManager.writeAndReadBytesAsPrimitives(new File("data-streams.txt"));

        ioManager.printFileFormattedWithLineNumberCharOriented(new File("normal.txt"), new FileOutputStream("dat10.txt"));
        System.out.println();
        ioManager.printFileFormattedWithLineNumberByteOrientedReadingFromStream(System.in, System.out);
//
////        InputStream is = null;
////        try {
////             is = new FileInputStream(f);
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } finally {
////            if (is != null) {
////                try {
////                    is.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
//
//        long startTime = System.currentTimeMillis();
//        try (InputStream is = new FileInputStream(f);
//             OutputStream os = new FileOutputStream("nova.txt", false);
//             BufferedInputStream bis = new BufferedInputStream(is);
//             BufferedOutputStream bos = new BufferedOutputStream(os);
//             DataInputStream dis = new DataInputStream(bis);
//             DataOutputStream dos = new DataOutputStream(bos);
//             ) {
//            int read;
////            int len = 1024;
////            byte[] buffer = new byte[len];
////            int offset = 0;
////            while ((read = is.read(buffer, offset, len)) != -1) {
//////                System.out.println("Byte: " + read);
//////                System.out.println("Character: " + (char) read);
//////                System.out.println();
////                os.write(buffer, offset, read);
////            }
//
////            while ((read = bis.read()) != -1) {
////                bos.write(read);
////            }
////            dos.writeDouble(54);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        long duration = System.currentTimeMillis() - startTime;
//        System.out.println("Duration: " + duration);
//        File copiedFile = new File("nova.txt");
//        System.out.println("Source File len: " + f.length());
//        System.out.println("Dest File len: " + copiedFile.length());

//        try (RandomAccessFile raf = new RandomAccessFile("dat2.txt","r");
//             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("nova.txt"))) {
//            for (long i = raf.length() - 1; i >=0; i--) {
//                raf.seek(i);
//                int read = raf.read();
//                bos.write(read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try (FileReader fr = new FileReader("dat2.txt");
//            BufferedReader br = new BufferedReader(fr);
//            BufferedWriter bw = new BufferedWriter(new FileWriter("nova.txt"));
//            PrintWriter pw = new PrintWriter(bw);
//            BufferedReader bir = new BufferedReader(new InputStreamReader(new FileInputStream("dat2.txt"), "UTF-8"))
//            ) {
//            int read;
//            String line;
//            while ((line = br.readLine()) != null) {
//                if (line.isEmpty()) {
//                    break;
//                }
//                pw.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }




    }
}
