class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {

        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();

        current.add("");

        while(index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            // Comma means OR
            if(ch == ',') {

                result.addAll(current);
                current.clear();
                current.add("");

                index++;
            }

            // Opening brace → recursively parse
            else if(ch == '{') {

                index++;

                Set<String> inside = parse(s);

                index++; // skip '}'

                current = multiply(current, inside);
            }

            // Normal character
            else {

                Set<String> next = new HashSet<>();

                for(String str : current) {
                    next.add(str + ch);
                }

                current = next;

                index++;
            }
        }

        result.addAll(current);

        return result;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for(String x : a) {
            for(String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}