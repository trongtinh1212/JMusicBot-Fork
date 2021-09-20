package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public abstract class ExampleCommandCode extends Command {
    // If u want to add own command quickly, you can use this example command
    public ExampleCommandCode() {
        // your strings, ints, and more (أنا روسي آسف)
    }
    public void onSlashCommand(SlashCommandEvent event)
    {
        if (!event.getName().equals("ping")) return;
        event.reply("Pong!");
    }
}
