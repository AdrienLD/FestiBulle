import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from '@/components/ui/select'
import { Popover, PopoverTrigger, PopoverContent } from '@/components/ui/popover'
import React, { useState } from 'react'
import { Checkbox } from '@/components/ui/checkbox'

const Filtres: React.FC = () => {
  const hours = Array.from({ length: 23 }, (_, i) => i + 1)
  const minutes = [ '00', '15', '30', '45' ]

  const [ ville, setVille ] = useState('')
  const [ type, setType ] = useState('')
  const [ people, setPeople ] = useState('')
  const [ startHourTime, setStartHourTime ] = useState('')
  const [ startMinuteTime, setStartMinuteTime ] = useState('')
  const [ freePaid, setFreePaid ] = useState(false)

  const filtrer = () => {
    console.log('Filtrer')
    console.log('Ville:', ville)
    console.log('Type:', type)
    console.log('People:', people)
    console.log('StartHourTime:', startHourTime)
    console.log('StartMinuteTime:', startMinuteTime)
    console.log('FreePaid:', freePaid)
  }

  return (
    <div className="flex flex-wrap bg-card p-4 rounded-lg shadow-lg w-full">
      <form className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 xl:grid-cols-6 gap-4 justify-between w-full">
        <div>
          <Label htmlFor="city">Ville</Label>
          <Input
            id="city"
            type="text"
            className="w-full"
            value={ville}
            onChange={(e) => setVille(e.target.value)}
            placeholder="Filtrer une ville"
          />
        </div>
        <div>
          <Label htmlFor="type">Type de soirée</Label>
          <Select 
            value={type} 
            onValueChange={(e) => setType(e)}
          >
            <SelectTrigger className="w-full">
              <SelectValue placeholder="Type de soirée"/>
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="TO">Tous</SelectItem>
              <SelectItem value="CL">Classique</SelectItem>
              <SelectItem value="JS">Jeux de sociétés</SelectItem>
              <SelectItem value="JV">Jeux vidéo</SelectItem>
              <SelectItem value="AU">Autres</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div>
          <Label htmlFor="people">Nombre de personnes</Label>
          <Input id="people" type="number" className="w-full" placeholder="Entrez un nombre" value={people} onChange={(e) => setPeople(e.target.value)}/>
        </div>
        <div>
          <Label htmlFor="start-time">Heure de début</Label>
          <Popover>
            <PopoverTrigger asChild>
              <Button variant="outline" className="w-full justify-start">
                <span id="start-time-button">{
                  startHourTime && startMinuteTime? `${startHourTime}:${startMinuteTime}`: 'Choisir heure'
                }</span>
              </Button>
            </PopoverTrigger>
            <PopoverContent className="w-full p-4">
              <div className="grid gap-4">
                <div className="grid grid-cols-2 gap-2">
                  <Select 
                    value={startHourTime} 
                    onValueChange={(e) => setStartHourTime(e)}
                  >
                    <SelectTrigger>
                      <SelectValue placeholder="Heure" />
                    </SelectTrigger>
                    <SelectContent>
                      {hours.map(hour => (
                        <SelectItem key={hour} value={hour.toString()}>{hour}</SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                  <Select
                    value={startMinuteTime} 
                    onValueChange={(e) => setStartMinuteTime(e)}
                  >
                    <SelectTrigger>
                      <SelectValue placeholder="Minute" />
                    </SelectTrigger>
                    <SelectContent>
                      {minutes.map(minute => (
                        <SelectItem key={minute} value={minute}>{minute}</SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
              </div>
            </PopoverContent>
          </Popover>
        </div>
        <div>
          <Label htmlFor="free-paid">Gratuit</Label>
          <br/>
          <Checkbox id="terms" checked={freePaid} onCheckedChange={(checked: boolean) => setFreePaid(checked)}/>
        </div>
        <div className="flex h-full w-full items-center ">
          <Button type="submit" className="w-full" variant="secondary" onClick={(e) => { e.preventDefault(); filtrer() }}>
                    Appliquer les filtres
          </Button>
        </div>
      </form>
    </div>
  )
}

export default Filtres
