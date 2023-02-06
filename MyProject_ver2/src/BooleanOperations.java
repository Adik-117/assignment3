class BooleanOperation extends AbstractCalculator {
    private boolean result;
    private boolean a;
    private boolean b;

    public BooleanOperation(boolean a, boolean b) {
        this.a = a;
        this.b = b;
    }

    public boolean and() {
        result = a && b;
        return result;
    }

    public boolean or() {
        result = a || b;
        return result;
    }

    public boolean not() {
        result = !a;
        return result;
    }
    @Override
    public int performOperation() {
        System.out.println("Enter a boolean operation:");
        System.out.println("1. AND");
        System.out.println("2. OR");
        System.out.println("3. NOT");
        int boolChoice = Main.scanner.nextInt();
        switch (boolChoice) {
            case 1:
                return and() ? 1 : 0;
            case 2:
                return or() ? 1 : 0;
            case 3:
                return not() ? 1 : 0;
            default:
                System.out.println("Invalid choice. Please try again.");
                return 0;
        }
    }
}