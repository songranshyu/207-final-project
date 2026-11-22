package use_case.itemize_transaction;

import entity.Transaction;

/**
 * The Itemize transaction Interactor
 */
public class ItemizeInteractor implements ItemizeInputBoundary{
    private final ItemizeDataAccessInterface itemizeDataAccessInterface;
    private final ItemizeOutputBoundary itemizeOutputBoundary;
    private final Transaction transaction;

    public ItemizeInteractor(ItemizeDataAccessInterface itemizeDataAccessInterface,
                             ItemizeOutputBoundary itemizeOutputBoundary,
                             Transaction transaction) {
        this.itemizeDataAccessInterface = itemizeDataAccessInterface;
        this.itemizeOutputBoundary = itemizeOutputBoundary;
        this.transaction = transaction;
    }

    @Override
    public void execute(ItemizeInputData itemizeInputData) {

    }
}



    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        if ("".equals(changePasswordInputData.getPassword())) {
            userPresenter.prepareFailView("New password cannot be empty");
        }
        else {
            final User user = userFactory.create(changePasswordInputData.getUsername(),
                    changePasswordInputData.getPassword());

            userDataAccessObject.changePassword(user);

            final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName());
            userPresenter.prepareSuccessView(changePasswordOutputData);
        }
    }
}