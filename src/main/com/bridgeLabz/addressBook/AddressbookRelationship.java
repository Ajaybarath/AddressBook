package main.com.bridgeLabz.addressBook;

public class AddressbookRelationship {

    int contactId;

    int relationshipId;

    public AddressbookRelationship(int contactId, int relationshipId) {
        this.contactId = contactId;
        this.relationshipId = relationshipId;
    }

    public AddressbookRelationship() {
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(int relationshipId) {
        this.relationshipId = relationshipId;
    }
}
