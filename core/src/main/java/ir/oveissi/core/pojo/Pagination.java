package ir.oveissi.core.pojo;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {
    public List<T> data = new ArrayList<>();
    public Metadata metadata;
}
