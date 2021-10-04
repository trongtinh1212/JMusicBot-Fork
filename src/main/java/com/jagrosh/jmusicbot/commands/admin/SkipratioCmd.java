package com.jagrosh.jmusicbot.commands.admin;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.commands.AdminCommand;
import com.jagrosh.jmusicbot.settings.Settings;

/**
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class SkipratioCmd extends AdminCommand
{
    public SkipratioCmd(Bot bot)
    {
        this.name = "setskip";
        this.help = "sets a server-specific skip percentage";
        this.arguments = "<0 - 100>";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    protected void execute(CommandEvent event)
    {
        try
        {
            int val = Integer.parseInt(event.getArgs().endsWith("%") ? event.getArgs().substring(0,event.getArgs().length()-1) : event.getArgs());
            if( val < 0 || val > 100)
            {
                event.replyError("The provided value must be between 0 and 100!");
                return;
            }
            Settings s = event.getClient().getSettingsFor(event.getGuild());
            s.setSkipRatio(val / 100.0);
            event.replySuccess("Skip percentage has been set to `" + val + "%` of listeners on *" + event.getGuild().getName() + "*");
        }
        catch(NumberFormatException ex)
        {
            event.replyError("Please include an integer between 0 and 100 (default is 55). This number is the percentage of listening users that must vote to skip a song.");
        }
    }
}