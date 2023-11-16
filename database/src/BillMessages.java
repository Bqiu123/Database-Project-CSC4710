public class BillMessages {
    private String billMessageID; // Corresponds to billMessageID INT AUTO_INCREMENT PRIMARY KEY
    private String userID;        // Corresponds to userID INTEGER
    private String billID;        // Corresponds to billID INTEGER
    private String msgTime;       // Corresponds to msgTime DATETIME
    private String price;         // Corresponds to price DOUBLE
    private String note;          // Corresponds to note VARCHAR(200)

    // Constructors
    public BillMessages() {
    }

    public BillMessages(String billMessageID) {
        this.billMessageID = billMessageID;
    }

    public BillMessages(String billMessageID, String userID, String billID, String msgTime, String price, String note) {
        this.billMessageID = billMessageID;
        this.userID = userID;
        this.billID = billID;
        this.msgTime = msgTime;
        this.price = price;
        this.note = note;
    }

    public BillMessages(String userID, String billID, String msgTime, String price, String note) {
        this.userID = userID;
        this.billID = billID;
        this.msgTime = msgTime;
        this.price = price;
        this.note = note;
    }

    // Getters and setters
    public String getBillMessageID() {
        return billMessageID;
    }

    public void setBillMessageID(String billMessageID) {
        this.billMessageID = billMessageID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
