class Solution {
    public String solution(String new_id) {
        StringBuffer sb = new StringBuffer();
		// 1단계 대문자를 소문자로 치환
		new_id = new_id.toLowerCase();
		int size = new_id.length();
		for (int i = 0; i < new_id.length(); i++) {
			if (Character.isLowerCase(new_id.charAt(i)))
				sb.append(new_id.charAt(i));
			// 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거
			if (new_id.charAt(i) == '-' || new_id.charAt(i) == '_' || new_id.charAt(i) == '.')
				sb.append(new_id.charAt(i));
			if (new_id.charAt(i) >= '0' && new_id.charAt(i) <= '9')
				sb.append(new_id.charAt(i));
			// 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
			if (i > 0 && sb.length()>1) {
				if (new_id.charAt(i) == '.' && sb.charAt(sb.length() - 2) == '.')
					sb.deleteCharAt(sb.length() - 1);
			}
		}

		// 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.

		if (sb.charAt(0) == '.')
			sb.deleteCharAt(0);
		if (sb.length() != 0) {
			if (sb.charAt(sb.length() - 1) == '.')
				sb.deleteCharAt(sb.length() - 1);
		}

		// 5단계 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if (sb.length()==0)
			sb.append("a");

		if (sb.length() >= 16) {
			sb.delete(15, sb.length());
			if (sb.charAt(sb.length() - 1) == '.')
				sb.deleteCharAt(sb.length() - 1);
		}
		while (sb.length() <= 2)
			sb.append(sb.charAt(sb.length() - 1));

        return sb.toString();
    }
}