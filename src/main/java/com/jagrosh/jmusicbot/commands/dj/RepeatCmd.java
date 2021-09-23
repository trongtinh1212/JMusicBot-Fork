/*
 * Copyright 2018 John Grosh <john.a.grosh@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.commands.DJCommand;
import com.jagrosh.jmusicbot.settings.RepeatMode;
import com.jagrosh.jmusicbot.settings.Settings;

/**
 *
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class RepeatCmd extends DJCommand
{
    public RepeatCmd(Bot bot)
    {
        super(bot);
        this.name = "repeat";
        this.help = "re-adds music to the queue when finished";
        this.arguments = "[track|queue|off]";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.guildOnly = true;
    }
    
    // override musiccommand's execute because we don't actually care where this is used
    @Override
    protected void execute(CommandEvent event) 
    {
        Settings settings = event.getClient().getSettingsFor(event.getGuild());
        switch (event.getArgs()) {
            case "track":
                settings.setRepeatMode(RepeatMode.TRACK);
                event.replySuccess("Repeat mod is now **track**");
                    break;
            case "queue":
                settings.setRepeatMode(RepeatMode.QUEUE);
                event.replySuccess("Repeat mod is now **queue**");
                    break;
            case "off":
                settings.setRepeatMode(RepeatMode.OFF);
                event.replySuccess("Repeat mod is now **off**");
                    break;
            default:
                event.replySuccess("Now mode is: " + "**" + settings.getRepeatMode().getName() + "**" );
        }
    }

    @Override
    public void doCommand(CommandEvent event) { /* Intentionally Empty */ }
}
