// ************************** Alfiyyah's Contribution **********************/

package ASS.covaxx.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

public class Certificate {
    @Id
    public ObjectId certID;

    public Map<String, String> files = new HashMap<>();

    public String certDate;
    public String certTime;
    public String vacResult;
    public String facility;
    public String summary;

    public String patientID;
}

// ************************** Alfiyyah's Contribution **********************/
