package task2613.command;


import task2613.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
