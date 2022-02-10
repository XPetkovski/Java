import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public class FileTester {
    public static void main(String[] args) throws IOException {
//        ExInterface exInterface = new ExInterfaceImpl();
//        exInterface.method();

        File file = new File("."); //same as new File("/home/davchev/Desktop/os-2020")
//        System.out.println(file.exists());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getName());
//        System.out.println(file.getPath());
//        System.out.println(file.isDirectory());
//        System.out.println(file.canRead());
//        System.out.println(file.length());
//
//        File newFile = new File("./nova.txt");
//        if (!newFile.exists()) {
//            boolean isCreated = newFile.createNewFile();
//            if (!isCreated) {
//                System.out.println("Couldn't be created!");
//            }
//        }

//        File[] files = file.listFiles();
//        assert files != null;
//        for (File f : files) {
//            System.out.println(String.format("Absolute path: %s, size(bytes): %d", f.getAbsolutePath(), f.length()));
//        }


        //list only directories and .java files
//        File[] newFiles = file.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File file, String s) {
//                File child = new File(file, s);
//                if (child.isDirectory() || (child.isFile() && s.endsWith(".java"))) {
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        assert newFiles != null;
//        for (File f : newFiles) {
//            System.out.println(String.format("Absolute path: %s, size(bytes): %d", f.getAbsolutePath(), f.length()));
//        }


        FileManager fileManager = new FileManagerImpl();

        File workingDirectory = fileManager.getCurrentWorkingDirectoryAsFile();

        File newFile = new File(workingDirectory, "nova.txt");
        fileManager.renameFile(newFile, "nova2.txt");

        newFile = new File(workingDirectory, "nova2.txt");
        fileManager.moveFile(newFile, "./folder");

        //this can be replaced with lambda
        fileManager.printDirectoryRecursive(workingDirectory, new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                File child = new File(file, s);
                return child.isDirectory() || (child.isFile() && s.endsWith(".java"));
            }
        });

        System.out.println();
        //copied everything from folder into new folder novi
        //now we will delete folder novi

        File fileToBeDeleted = new File("./novi/nova2.txt");
        if (!fileManager.deleteFile(fileToBeDeleted)) {
            System.out.println("Can not delete directory that contains files like a normal file");
        } else {
            System.out.println("File deleted successfully");
        }


        File directoryToBeDeleted = new File("./novi");
        if (!fileManager.deleteFile(directoryToBeDeleted)) {
            System.out.println("Can not delete directory that contains files like a normal file");
        }

        if (fileManager.deleteDirectoryRecursive(directoryToBeDeleted)) {
            System.out.println("Directory deleted successfully");
        }


        System.out.println();
        List<File> list = fileManager.getAllFilesRecursive(new File("./folder"));
        list.forEach(item -> System.out.println(item.getAbsolutePath()));
    }
}
