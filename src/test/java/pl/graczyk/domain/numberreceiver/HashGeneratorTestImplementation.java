package pl.graczyk.domain.numberreceiver;

public class HashGeneratorTestImplementation implements HashGenerable {
    private final String hash;

    HashGeneratorTestImplementation(String hash) {
        this.hash = hash;
    }

    public HashGeneratorTestImplementation() {
        hash = "123";
    }

    @Override
    public String getHash() {
        return hash;
    }
}
