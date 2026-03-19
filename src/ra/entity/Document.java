package ra.entity;
import java.util.Scanner;

public class Document {
    String documentId;
    String documentName;
    double fileSize;
    int downloads;

    public Document(String documentId, String documentName, double fileSize, int downloads) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.fileSize = fileSize;
        this.downloads = downloads;
    }
    public Document() {
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }
    // Nhap thong tin tai lieu, yeu cau nhap lai neu thong tin sai yeu cau
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ma tai lieu:");
        if(this.documentId == null || this.documentId.isEmpty()) {
            this.documentId = scanner.nextLine();
        }
        System.out.println("Nhap ten tai lieu:");
        if(this.documentName == null || this.documentName.isEmpty()) {
            this.documentName = scanner.nextLine().trim();
        }
        System.out.println("Nhap kich thuoc tai lieu:");
        if(this.fileSize > 0) {
            this.fileSize = scanner.nextDouble();
        }
        System.out.println("Nhap so lan tai tai lieu:");
        if(this.downloads >= 0) {
            this.downloads = scanner.nextInt();
        }
    }



    public void displayData() {
        System.out.println("============Thong tin tai lieu============");
        System.out.println("|Ma tai lieu: " + this.documentId+"                   |");
        System.out.println("|Ten tai lieu: " + this.documentName+"                |");
        System.out.println("|Kich thuoc tai lieu: " + this.fileSize+"             |");
        System.out.println("|So lan tai tai lieu: " + this.downloads+"            |");
        System.out.println("==========================================");
    }
}
