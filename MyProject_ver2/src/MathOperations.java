 class MathOperation extends AbstractCalculator {

    private int a;
    private int b;
    public MathOperation(int a,int b){
        this.a=a;
        this.b=b;
    }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int add() {
        result = a + b;
        return result;
    }

    public int subtract() {
        result = a - b;
        return result;
    }

    public int multiply() {
        result = a * b;
        return result;
    }

    public int divide() {
        result = a / b;
        return result;
    }

    @Override
    public int performOperation() {
        System.out.println("Enter a mathematical operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        int mathChoice = Main.scanner.nextInt();
        switch (mathChoice) {
            case 1:
                return add();
            case 2:
                return subtract();
            case 3:
                return multiply();
            case 4:
                return divide();
            default:
                System.out.println("Invalid choice. Please try again.");
                return 0;
        }
    }
    }
