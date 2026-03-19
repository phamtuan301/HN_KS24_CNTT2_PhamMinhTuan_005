package ra.business;
import ra.entity.Document;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
// doi tuong DocumentBusiness chi duoc ton tai 1 lan, su dung singleton pattern
// Cac phuong thuc su dung java 8
public class DocumentBusiness {
    private List<Document> documents;
    private static DocumentBusiness instance;
    private DocumentBusiness() {
    }
    public static DocumentBusiness getInstance() {
        if (instance == null) {
            instance = new DocumentBusiness();
        }
        return instance;
    }
    // Hien thi toan bo danh sach document, neu rong thi in loi
    public void displayAll() {
        if (documents == null) {
            System.out.println("Danh sach tai lieu rong!");
            return;
        }
        for (Document document : documents) {
            document.displayData();
        }
    }
    // Them document vao danh sach, neu ID da ton tai thi in loi, dung try catch de bat loi trung lap
    public void addDocument(Document document) {
        if(documents == null) {
            documents = new ArrayList<>();
        }
        if(documents.stream().anyMatch(d -> d.getDocumentId().equals(document.getDocumentId()))) {
            System.out.println("Ma tai lieu da ton tai!");
            return;
        }
        documents.add(document);
    }
    // Cho nguoi dung sua thong tin cua document theo ID
    public void updateDocument(Document document, String documentId) {
        if(documents == null) {
            return;
        }
        for (Document doc : documents) {
            if (doc.getDocumentId().equals(documentId)) {
                System.out.println("Chon truong can sua:");
                System.out.println("1. Ten tai lieu");
                System.out.println("2. Kich thuoc tai lieu");
                System.out.println("3. So lan tai tai lieu");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Nhap ten tai lieu moi:");
                        doc.setDocumentName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhap kich thuoc tai lieu moi:");
                        doc.setFileSize(scanner.nextDouble());
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Nhap so lan tai tai lieu moi:");
                        doc.setDownloads(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                        documents.removeIf(documents -> documents.getDocumentId().equals(documentId));
                }
            }
        }
    };
    // Tim kiem tai lieu theo ten, khong phan biet hoa thuong, tra ve document neu tim thay, khong thay thi in loi
    public Document findDocumentByName(String documentName) {
        if (documents == null) {
            System.out.println("Danh sach tai lieu rong!");
            return null;
        }
        return documents.stream().filter(doc -> doc.getDocumentName().toLowerCase().equals(documentName.toLowerCase())).findFirst().orElse(null);

    }
    // Xoa tai lieu theo ID, khong tim thay thi in loi
    public void deleteDocumentById(String documentId) {
        if(documents == null) {
            return;
        }
        Document document = documents.stream().filter(doc -> doc.getDocumentId().equals(documentId)).findFirst().orElse(null);
        if(document == null) {
            System.out.println("Khong tim thay tai lieu!");
            return;
        }
        documents.remove(document);
    }
    // Sap xep danh sach tai lieu theo so lan tai giam dan, tra ve danh sach sau khi sap xep
    public List<Document> sortDocumentByDownloads() {
        if (documents == null) {
            return null;
        }
        return documents.stream().sorted(Comparator.comparingInt(Document::getDownloads)).toList();
    }
    // Loc danh sach tai lieu co so lan tai >= 1000, tra ve danh sach sau khi loc
    public List<Document> filterDocument(int downloads) {
        if (documents == null) {
            return null;
        }
        return documents.stream().filter(doc -> doc.getDownloads() >= downloads).toList();
    }
}

