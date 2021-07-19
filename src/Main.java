import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        String userName;
        String password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Здравствуйте! Напишите количество пользователей.");
        n = scanner.nextInt();
        User [] users = new User[n] ;
        for (int i = 0; i < users.length; i++) {
            System.out.println("Введите username " + (i + 1) + "-го пользователя");
            userName = scanner.next();
            System.out.println("Введите password " + (i + 1) + "-го пользователя");
            password = scanner.next();
            users[i] = new User(userName, password);
            System.out.println();
        }
        System.out.println("\nСписок всех пользователей:");
        for (User user : users) System.out.println("1) " + user.getUserName());
        while (true){
            System.out.println("Введите номер действия, которое вы хотите совершить:");
            System.out.println("1) Генерация случайного надеждного пароля\n2) Проверка надежности пароля\n3) Изменение пароля с проверкой его надежности\n4) Печать данных всех пользователей\n5) Выход");
            int key = scanner.nextInt();
            if (key == 5) break;
            switch (key){
                case 1:
                    int lengthLow;
                    int lengthUp;
                    int lengthDigits;
                    int lengthPunctuation;
                    System.out.println("Правила надежного пароля:\n1) Длина больше или равна 8\n2) Содержит хотя бы одну прописную латинскую букву\n3) Содержит хотя бы одну заглавную латинскую букву");
                    System.out.println("4) Содержит хотя бы одну цифру\n5) Содержит хотя бы один символ пунктуации");
                    do{
                        System.out.println("Введите в строку через тире: длину латинских прописных букв, заглавных букв, цифр, символов пунктуации");
                        String lengths = scanner.next();
                        String [] lengthsMass = lengths.split("-");
                        lengthLow = Integer.parseInt(lengthsMass[0]);
                        lengthUp = Integer.parseInt(lengthsMass[1]);
                        lengthDigits = Integer.parseInt(lengthsMass[2]);
                        lengthPunctuation = Integer.parseInt(lengthsMass[3]);
                        if((lengthLow == 0) || (lengthUp == 0) || (lengthDigits == 0) || (lengthPunctuation == 0) || (lengthLow + lengthUp + lengthDigits + lengthPunctuation < 8))
                            System.out.println("Ошибка! Нарушено одно или несколько правил надежности пароля\nВведите длины еще раз\n");
                        else break;
                    } while(true);
                    String securePass = generateRandomPassword(lengthLow, lengthUp, lengthDigits, lengthPunctuation);
                    System.out.println("Надежный пароль - " + securePass);
                    System.out.println("Какому пользователю по списку присвоить данный пароль?");
                    int number = scanner.nextInt();
                    users[number-1].setPass(securePass);
                    break;
                case 2:
                    System.out.println("Введите номер пользователя по списку, чей пароль проверить на надежность?");
                    number = scanner.nextInt();
                    reliabilityCheck(users[number-1].getPass());
                    break;
                case 3:
                    System.out.println("Введите новый пароль");
                    String newPassword = scanner.next();
                    reliabilityCheck(newPassword);
                    System.out.println("Учтите проверку надежности пароля!");
                    System.out.println("Введите номер пользователя, которому присвоить данный пароль\nЕсли пароль не нужно присваивать, введите 0");
                    number = scanner.nextInt();
                    if (number != 0)
                        users[number-1].setPass(newPassword);
                    break;
                case 4:
                    for (int i = 0; i < users.length; i++)
                        System.out.println( (i+1) + ") Username: " + users[i].getUserName() + ". Password: " + users[i].getPass() + ".");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + key);
            }
        }
    }
    public static String generateRandomPassword(int lengthLow, int lengthUp, int lengthDigits, int lengthPunctuation) {
        final char[] lowChar = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] upChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        final char[] digits = "0123456789".toCharArray();
        final char[] punctuation = "!@#$%&*()_+-=[]|,./?><".toCharArray();
        final Random rnd = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < lengthLow; i++) {
            char a = lowChar[rnd.nextInt(lowChar.length)];
            password.append(a);
        }
        for (int i = 0; i < lengthUp; i++) {
            char a;
            a = upChar[rnd.nextInt(upChar.length)];
            password.append(a);
        }
        for (int i = 0; i < lengthDigits; i++) {
            char a;
            a = digits[rnd.nextInt(digits.length)];
            password.append(a);
        }
        for (int i = 0; i < lengthPunctuation; i++) {
            char a;
            a = punctuation[rnd.nextInt(punctuation.length)];
            password.append(a);
        }
        List<String> letters = Arrays.asList(password.toString().split(""));
        Collections.shuffle(letters);
        StringBuilder shuffled = new StringBuilder();
        for (String letter : letters) {
            shuffled.append(letter);
        }
        return shuffled.toString();
    }
    public static void reliabilityCheck(String password) {
        boolean hasDigit = false;
        boolean hasPunctuation = false;
        if ((password.length() < 8) || (password.equals(password.toLowerCase())) || (password.equals(password.toUpperCase())))
                System.out.println("Пароль ненадёжный");
            else {
                for (int i = 0; i < password.length(); i++) {
                    char x = password.charAt(i);
                    if (Character.isDigit(x))
                        hasDigit = true;
                    if (password.contains(Character.toString(x)))
                        hasPunctuation = true;
                }
                if (hasDigit && hasPunctuation)
                    System.out.println("Пароль надёжный.");
            }
        }
}
