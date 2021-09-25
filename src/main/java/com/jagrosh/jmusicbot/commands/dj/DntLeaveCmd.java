package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class DntLeaveCmd extends DJCommand {
    public DntLeaveCmd(Bot bot) {
        super(bot);
        this.name = "24/7";
        this.help = "Toggle 24/7 mode";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void doCommand(CommandEvent event) {

    }
}
