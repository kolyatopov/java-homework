package edu.phystech.hw2.analyzer;


import java.util.List;

public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private static final List<String> NEGATIVE_SMILES = List.of(":(", "=(", ":|");

    public NegativeTextAnalyzer() {
        super(NEGATIVE_SMILES, Label.NEGATIVE);
    }

    @Override
    protected boolean containsKeyword(String text, String keyword) {
        if (!":|".equals(keyword)) {
            return text.contains(keyword);
        }
        int from = 0;
        while (from <= text.length() - 2) {
            int idx = text.indexOf(":|", from);
            if (idx == -1) {
                return false;
            }
            boolean followedByAnotherPipe = idx + 2 < text.length() && text.charAt(idx + 2) == '|';
            if (!followedByAnotherPipe) {
                return true;
            }
            from = idx + 2;
        }
        return false;
    }
}
