package com.vyo.robologger.main.boot;

/**
 * Decides whether to wipe database on boot or update database on boot or simply startup database with old entries if available for api access
 */
public enum BootBehaviour {
    clean_boot,update_boot,no_action_boot
}
