package ra.presentation;
import ra.business.DocumentBusiness;
import ra.entity.Document;
import java.util.Scanner;
public class DocumentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            int choice;
            System.out.println("**********Quan ly tai lieu so **********");
            System.out.println("1. Hien thi danh sach toan bo tai lieu");
            System.out.println("2. Them moi tai lieu");
            System.out.println("3. Cap nhat thong tin tai lieu theo ma tai lieu");
            System.out.println("4. Xoa tai lieu theo ma tai lieu");
            System.out.println("5. Tim kiem tai lieu theo ten");
            System.out.println("6. Loc danh sach tai lieu pho bien ( downloads >= 1000)");
            System.out.println("7. Sap xep danh sach tai lieu theo so lan tai giam dan");
            System.out.println("8. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // Hien thi danh sach toan bo tai lieu
                    DocumentBusiness.getInstance().displayAll();
                    break;
                case 2:
                    // Them moi tai lieu, cho nguoi dung nhap so lan them
                    // Dung try catch de bat loi trung lap, khong lam chuong trinh dung dot ngot
                    System.out.println("Nhap so tai lieu muon them moi:");
                    int addDownloads = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < addDownloads; i++) {
                        System.out.println("Nhap thong tin tai lieu moi:");
                        Document document = new Document();
                        document.inputData();
                        DocumentBusiness.getInstance().addDocument(document);
                    }
                    break;
                case 3:
                    // Cap nhat thong tin tai lieu theo ma tai lieu
                    System.out.println("Nhap ma tai lieu can sua:");
                    String documentId = scanner.nextLine();
                    DocumentBusiness.getInstance().updateDocument(new Document(), documentId);
                    break;
                case 4:
                    // Xoa tai lieu theo ma tai lieu
                    System.out.println("Nhap ma tai lieu can xoa:");
                    String deleteDocumentId = scanner.nextLine();
                    DocumentBusiness.getInstance().deleteDocumentById(deleteDocumentId);
                    break;
                case 5:
                    // Tim kiem tai lieu theo ten
                    System.out.println("Nhap ten tai lieu can tim:");
                    String documentName = scanner.nextLine();
                    DocumentBusiness.getInstance().findDocumentByName(documentName);
                    if (DocumentBusiness.getInstance().findDocumentByName(documentName) != null) {
                        DocumentBusiness.getInstance().findDocumentByName(documentName).displayData();
                    } else {
                        System.out.println("Khong tim thay tai lieu!");
                    }
                    break;
                case 6:
                    // Loc danh sach tai lieu pho bien ( downloads >= 1000)
                    DocumentBusiness.getInstance().filterDocument(1000);
                    DocumentBusiness.getInstance().displayAll();
                    break;
                case 7:
                    // Sap xep danh sach tai lieu theo so lan tai giam dan
                    DocumentBusiness.getInstance().sortDocumentByDownloads().forEach(Document::displayData);
                    break;
                case 8:
                    // Thoat chuong trinh
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        } while (true);
    }
}