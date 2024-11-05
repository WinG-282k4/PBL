package Models.ZabbixAPI;

// Thêm class để lưu thông tin ổ đĩa
class DiskInfo {
    String name;
    String lastValue;

    public DiskInfo(String name, String lastValue) {
        this.name = name;
        this.lastValue = lastValue;
    }
}