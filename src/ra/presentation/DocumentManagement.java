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
                    DocumentBusiness.getInstance().displayAll();
                    break;

                case 2:
                    System.out.println("Nhap so tai lieu muon them moi:");
                    int n = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < n; i++) {
                        Document document = new Document();
                        document.inputData();
                        DocumentBusiness.getInstance().addDocument(document);
                    }
                    break;

                case 3:
                    System.out.println("Nhap ma tai lieu can sua:");
                    String id = scanner.nextLine();
                    DocumentBusiness.getInstance().updateDocument(id); // FIX
                    break;

                case 4:
                    System.out.println("Nhap ma tai lieu can xoa:");
                    String deleteId = scanner.nextLine();
                    DocumentBusiness.getInstance().deleteDocumentById(deleteId);
                    break;

                case 5:
                    System.out.println("Nhap ten tai lieu can tim:");
                    String name = scanner.nextLine();

                    Document doc = DocumentBusiness.getInstance().findDocumentByName(name); // FIX
                    if (doc != null) {
                        doc.displayData();
                    } else {
                        System.out.println("Khong tim thay tai lieu!");
                    }
                    break;

                case 6:
                    DocumentBusiness.getInstance()
                            .filterDocument(1000)
                            .forEach(Document::displayData); // FIX
                    break;

                case 7:
                    DocumentBusiness.getInstance()
                            .sortDocumentByDownloads()
                            .forEach(Document::displayData);
                    break;

                case 8:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (true);
    }
}