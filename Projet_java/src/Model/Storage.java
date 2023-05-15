package Model;

public class Storage {
    private Integer storageId;
    private Integer room;
    private Integer rackNumber;
    private Integer lineNumber;

    public Storage(Integer room,Integer rackNumber,Integer lineNumber){
        this.room = room;
        this.rackNumber = rackNumber;
        this.lineNumber = lineNumber;
    }
    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public Integer getRoom() {
        return room;
    }

    public Integer getRack() {
        return rackNumber;
    }

    public Integer getLine() {
        return lineNumber;
    }
}
