package csed.database.orderprocessingbackend.model.Requests;

public record OrderRequest(Long ISBN, int quantity) {

    public String toString() {
        return "(" + ISBN + ", " + quantity + ")";
    }
};