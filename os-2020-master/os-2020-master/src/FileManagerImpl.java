import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManagerImpl implements FileManager {

    @Override
    public boolean exists(String path) {
        File f = new File(path);
        return f.exists();
    }

    @Override
    public boolean exists(File file) {
        return file.exists();
    }

    private void validateIfFileExists(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("File with path: %s doesn't exist!", file.getAbsolutePath()));
        }
    }

    @Override
    public long length(File f) {
        return f.length();
    }

    @Override
    public boolean isFile(String path) throws FileNotFoundException {
        File file = new File(path);
        this.validateIfFileExists(file);
        return file.isFile();
    }

    @Override
    public boolean isDirectory(String path) throws FileNotFoundException {
        File file = new File(path);
        this.validateIfFileExists(file);
        return file.isDirectory();
    }

    @Override
    public boolean isHiddenFile(String path) throws FileNotFoundException {
        File file = new File(path);
        this.validateIfFileExists(file);
        return file.isHidden();
    }

    @Override
    public String getAbsolutePathFromWorkingDirectory() {
        File file = new File(".");
        return file.getAbsolutePath();
    }

    @Override
    public File getCurrentWorkingDirectoryAsFile() {
        return new File(".");
    }

    @Override
    public File getParent(File file) {
        File f = new File(file.getAbsolutePath());
        return f.getParentFile();
    }

    @Override
    public boolean createDirectoryIfParentExists(String path) {
        File file = new File(path);
        return file.mkdir();
    }

    @Override
    public boolean createDirectoryWithParentDirectories(String path) {
        File file = new File(path);
        return file.mkdirs();
    }

    @Override
    public boolean createFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            System.out.println("File already exists!"); //you can throw exception...
            return false;
        }
        return file.createNewFile();
    }

    @Override
    public boolean moveAndRenameFile(File file, String newName, String newParent) {
        File parent = new File(newParent);
        if (!parent.isDirectory()) {
            //you can throw exception...
            System.out.println(String.format("Parent with path: %s is not a directory!", parent.getAbsolutePath()));
            return false;
        }

        File renamedFile = new File(parent, newName);
        if (renamedFile.exists()) {
            System.out.println("File already exists!"); //you can throw exception...
            return false;
        }

        return file.renameTo(renamedFile);
    }

    @Override
    public boolean moveFile(File file, String newParent) {
        return this.moveAndRenameFile(file, file.getName(), newParent);
    }

    @Override
    public boolean renameFile(File file, String newName) {
        return this.moveAndRenameFile(file, newName, file.getParent());
    }

    @Override
    public boolean copyFile(String pathFrom, String pathTo) {
        //this will be implemented next week (first we need to finish i/o)
        return false;
    }

    @Override
    public void printDirectory(File folder, FilenameFilter filenameFilter) {
        File[] files;
        if (filenameFilter != null) {
            files = folder.listFiles(filenameFilter);
        } else {
            files = folder.listFiles();
        }
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    @Override
    public void printDirectoryRecursive(File folder, FilenameFilter filenameFilter) {
        File[] files;
        if (filenameFilter != null) {
            files = folder.listFiles(filenameFilter);
        } else {
            files = folder.listFiles();
        }
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getAbsolutePath());
                if (f.isDirectory()) {
                    this.printDirectoryRecursive(f, filenameFilter);
                }
            }
        }
    }

    @Override
    public boolean deleteFile(File file) {
        return file.delete();
    }

    @Override
    public boolean deleteDirectoryRecursive(File directory) {
        if (!directory.isDirectory()) {
            //you can throw exception...
            System.out.println(String.format("File with path: %s is not a directory!", directory.getAbsolutePath()));
            return false;
        }

        if (!directory.canWrite()) {
            System.out.println(String.format("You don't have write permission to delete this directory: %s ", directory.getAbsolutePath()));
            return false;
        }

        File[] files = directory.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.isDirectory()) {
                this.deleteDirectoryRecursive(f);
            } else {
                boolean deleted = f.delete();
                if (!deleted) {
                    System.out.println("File with path: %s can not be deleted!: " + f.getAbsolutePath());
                }
            }
        }
        return directory.delete();
    }

    @Override
    public List<File> getAllFilesRecursive(File directory) {
        if (!directory.exists() || !directory.isDirectory()) {
            //you can throw exception or print message... or return empty list...
            return null;
        }
        List<File> list = new ArrayList<>();
        this.fillTheListRecursive(directory, list);
        return list;
    }

    private void fillTheListRecursive(File directory, List<File> list) {
        File[] files = directory.listFiles();
        assert files != null;
        for (File f : files) {
            list.add(f);
            if (f.isDirectory()) {
                fillTheListRecursive(f, list);
            }
        }
    }
}
