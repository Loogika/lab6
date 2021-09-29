package types;

public enum Commands {
    help,
    info,
    show,
    insert,
    update,
    remove,
    clear,
    save,
    execute_script,
    exit,
    remove_lower,
    history,
    filter_by_semester_enum,
    filter_starts_with_name,
    print_descending;

    private static Commands[] list = Commands.values();

    public static Commands getCommand(int id){
        return list[id-1];
    }
}
