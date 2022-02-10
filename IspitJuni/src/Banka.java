import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Bank {
            Account[] accounts = new Account[5];
            Integer total = 0;

            void addAccount(Account account) {
                this.accounts[total++] = account;
            }

            public static void main(String[] args) throws InterruptedException {
                Bank bank = new Bank();
                bank.addAccount(new Account("AAA", 100.0));
                bank.addAccount(new Account("BBB", 100.0));
                bank.addAccount(new Account("CCC", 100.0));
                bank.addAccount(new Account("DDD", 100.0));
                bank.addAccount(new Account("EEE", 100.0));

                for (int i = 0; i < 1000; ++i) {
                    // треба да се извршуваат паралелно овие акции
                    new Deposit(bank.accounts[i%5], 1.0);
                    new Withdraw(bank.accounts[i%5], 0.5);
                }

                for (int i = 0; i < bank.total; ++i) {
                    System.out.println(bank.accounts[i]);
                }
                System.out.println(Deposit.history);
                System.out.println(Withdraw.history);
            }
        }

        class Account {
            public String name;
            public Double balance;

            public Account(String name, Double balance) {
                this.name = name;
                this.balance = balance;
            }

            void deposit(Double amount) throws InterruptedException {
                Thread.sleep(new Random().nextInt(1000));
                this.balance += amount;
            }

            void withdraw(Double amount) throws InterruptedException {
                Thread.sleep(new Random().nextInt(1000));
                this.balance -= amount;
            }

            @Override
            public String toString() {
                return this.name + " -> " + this.balance;
            }
        }

        class Deposit {
            final static List<String> history = new LinkedList<>();
            Account account;
            Double amount;

            public Deposit(Account account, Double amount) {
                this.account = account;
                this.amount = amount;
            }

            public void doDeposit() {
                this.account.deposit(amount);
                history.add("+" + this.amount);
            }
        }

        class Withdraw {
            final static List<String> history = new LinkedList<>();
            Account account;
            Double amount;

            public Withdraw(Account account, Double amount) {
                this.account = account;
                this.amount = amount;
            }

            public void doWithdraw() {
                this.account.withdraw(amount);
                history.add("-" + this.amount);
            }
        }
