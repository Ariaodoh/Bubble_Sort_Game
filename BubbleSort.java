package com.ljmu.comp7501.eodoh;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BubbleSort {

    public static void main(String[] args){

        //create array list to hold user text input as a collection of strings 
        List<String> inputText = new ArrayList<>();

        //Prompt user entry 
        System.out.println("\n \nWelcome to Bubble Sort, please enter the text or strings you would like to sort: [Type 'END' to stop]");

        //add all the user input from scanner object to array list. Use try with resource block to handle resource management  
        try (Scanner scanner = new Scanner(System.in)) {
            inputText = scanner.tokens()
                .takeWhile(token -> !token.equals("END"))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);   
            
            //print user option for alphanumeric or reversed alphanumeric sorts
            System.out.println("\n \nHow would you like to sort this text: \n \n1. Alphanumeric Bubble Sort \n2. Reversed Alphanumeric Bubble Sort");
            
            //process user text with handler method that calls the bubblesort or resversed bubblesort methods 
            userInputHandler(scanner, inputText);
        }

    }



    /**
     * --------------------------[USER INPUT HANDLER METHOD]-------------------------------
     * This method processes the user option selection by calling relevant methods for sorting and printing
     * this method receives user option selection from the user option reader method 
     * @param scanner scanner object to read user option entry from console. 
     * @param inputText arraylist of user input text for sorting 
     */
    private static void userInputHandler(Scanner scanner, List<String> inputText) {
        Optional<Integer> option = userOptionReader(scanner);

        option.ifPresentOrElse(
            choice -> {
                if (choice == 1) {
                    bubbleSort(inputText);
                    printList(inputText);
                } else if (choice == 2) {
                    bubbleSortReversed(inputText);
                    printList(inputText);
                } else {
                    System.out.println("\n \nInvalid Input, enter a number from the options");
                }
            },
            () -> System.out.println("\n \nInvalid Input, enter a number from the options")
        );
    }

   
   
   
    /**
     * --------------------------[USER OPTION READER METHOD]-------------------------------
     * This method reads console for user option selection and assigns value to an optional object 
     * method manages null entry with the optional.empty() method
     * @param scanner receives scanner object from main to read console input
     * @return returns optional object with interger value indicating user selection from menu printed to console in main method
     */
    private static Optional<Integer> userOptionReader(Scanner scanner) {
        return scanner.hasNextInt() ? Optional.of(scanner.nextInt()) : Optional.empty();
    }



    /**
     * -------------------------[BUBBLE SORT METHOD]-----------------------------------
     * This method sorts the user input text to an alphanumeric array list. 
     * the sorts uses the objects natural ordering which is the default of the compareTo method adopted
     * this method alters the original arraylist passed in. It is used for this side-effect
     * @param list String array list of user input text for sorting 
     */
    public static void bubbleSort(List<String> list) {
        int n = list.size();
        boolean swapped;

        do {
            swapped = false;

            for (int i = 1; i < n; i++) {
                // Compare in aphnumeric order, maintaining already ordered items 
                if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                    // Swap elements if they are in the wrong order
                    String temp = list.get(i - 1);
                    list.set(i - 1, list.get(i));
                    list.set(i, temp);
                    swapped = true;
                }
            }

            // After each pass, the largest unsorted element is moved to the end. The effective array list size is decreased
            n--;
        } while (swapped);
    }


    /**
     * -----------------------------[BUBBLE SORT REVERSED METHOD]----------------------------------------
     * This method sorts the user input text to a reversed alphanumeric array list. 
     * the sorts uses the objects natural ordering which is the default of the compareTo method adopted
     * this method alters the original arraylist passed in. It is used for this side-effect
     * @param list String array list of user input text for sorting 
     */
    public static void bubbleSortReversed(List<String> list) {
        int n = list.size();
        boolean swapped;

        do {
            swapped = false;

            for (int i = 1; i < n; i++) {
                // Compare in reversed alphanumeric order, maintaining already ordered items 
                if (list.get(i - 1).compareTo(list.get(i)) < 0) {
                    // Swap elements if they are in the wrong order
                    String temp = list.get(i - 1);
                    list.set(i - 1, list.get(i));
                    list.set(i, temp);
                    swapped = true;
                }
            }

            // After each pass, the smallest unsorted element is moved to the end. The effective array list size is decreased
            n--;
        } while (swapped);
    }



    /**
     * ------------------------------[PRINT LIST METHOD]--------------------------------------
     * This method prints array to console. This method is called after sorting thus only sorted arrays are printed 
     * @param list array list of user input text sorted with either the bubble sort or reversed bubble sort methods 
     */
    public static void printList(List<String> list) {

        list.forEach(System.out::println);
       
    }


}//----------------------------------[END OF BUBBLE SORT CLASS]---------------------------------
