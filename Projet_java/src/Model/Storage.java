package Model;

public class Storage {
    private Integer storageId;
    private Integer room;
    private Integer rackNumber;
    private Integer lineNumber;

    public Storage(Integer storageId,Integer room,Integer rackNumber,Integer lineNumber){
        this.storageId = storageId;
        this.room = room;
        this.rackNumber = rackNumber;
        this.lineNumber = lineNumber;
    }
}
