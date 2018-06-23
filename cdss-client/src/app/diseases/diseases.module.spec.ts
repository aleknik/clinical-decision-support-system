import { DiseasesModule } from './diseases.module';

describe('DiseasesModule', () => {
  let diseasesModule: DiseasesModule;

  beforeEach(() => {
    diseasesModule = new DiseasesModule();
  });

  it('should create an instance', () => {
    expect(diseasesModule).toBeTruthy();
  });
});
