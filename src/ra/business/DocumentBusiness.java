package ra.business;

import ra.entity.Document;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DocumentBusiness {
    private List<Document> documents = new ArrayList<>();
    private static DocumentBusiness instance;

    private DocumentBusiness() {
    }

    public static synchronized DocumentBusiness getInstance() {
        if (instance == null) {
            instance = new DocumentBusiness();
        }
        return instance;
    }

    public void displayAll() {
        if (documents.isEmpty()) {
            System.out.println("Danh sach tai lieu rong!");
            return;
        }
        documents.forEach(Document::displayData);
    }

    public void addDocument(Document document) {
        if (documents.stream().anyMatch(d -> d.getDocumentId().equals(document.getDocumentId()))) {
            System.out.println("Ma tai lieu da ton tai!");
            return;
        }
        documents.add(document);
    }


    public void updateDocument(String documentId) {
        if (documents.isEmpty()) {
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
                }
                return;
            }
        }
        System.out.println("Khong tim thay tai lieu!");
    }

    public Document findDocumentByName(String documentName) {
        if (documents.isEmpty()) {
            System.out.println("Danh sach tai lieu rong!");
            return null;
        }

        return documents.stream()
                .filter(doc -> doc.getDocumentName().toLowerCase().contains(documentName.toLowerCase())) // FIX: contains
                .findFirst()
                .orElse(null);
    }

    public void deleteDocumentById(String documentId) {
        if (documents.isEmpty()) {
            return;
        }

        Document document = documents.stream()
                .filter(doc -> doc.getDocumentId().equals(documentId))
                .findFirst()
                .orElse(null);

        if (document == null) {
            System.out.println("Khong tim thay tai lieu!");
            return;
        }
        documents.remove(document);
    }

    public List<Document> sortDocumentByDownloads() {
        if (documents.isEmpty()) {
            return null;
        }

        return documents.stream()
                .sorted(Comparator.comparingInt(Document::getDownloads).reversed())
                .toList();
    }

    public List<Document> filterDocument(int downloads) {
        if (documents.isEmpty()) {
            return null;
        }

        return documents.stream()
                .filter(doc -> doc.getDownloads() >= downloads)
                .toList();
    }
}