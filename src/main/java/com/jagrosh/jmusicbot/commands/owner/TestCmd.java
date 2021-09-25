package com.jagrosh.jmusicbot.commands.owner;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.commands.OwnerCommand;
import com.jagrosh.jmusicbot.lang.Lang;

import java.io.IOException;

public class TestCmd extends OwnerCommand {
    public TestCmd() {
        this.name = "test";
    }
    @Override
    protected void execute(CommandEvent event) {
        Lang lang = new Lang(event.getGuild().getIdLong());
        event.reply(lang.getString("xui"));
    }
}